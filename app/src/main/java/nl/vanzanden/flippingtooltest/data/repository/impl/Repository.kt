package nl.vanzanden.flippingtooltest.data.repository.impl

import io.reactivex.Observable
import nl.vanzanden.flippingtooltest.BuildConfig
import nl.vanzanden.flippingtooltest.app.FlippingToolApp
import nl.vanzanden.flippingtooltest.data.api.ApiService
import nl.vanzanden.flippingtooltest.data.network.Response
import nl.vanzanden.flippingtooltest.data.repository.preferences.PreferenceHelper
import nl.vanzanden.flippingtooltest.domain.entities.Recommendation
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class Repository : IRepository {

    private val apiService: ApiService

    @Inject
    lateinit var prefs: PreferenceHelper

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(3, TimeUnit.SECONDS)
            .writeTimeout(3, TimeUnit.SECONDS)
            .addInterceptor { chain: Interceptor.Chain ->
                val request = chain.request()
                val requestBuilder = request.newBuilder()
                requestBuilder.addHeader("Accept", "application/json")
                val identifierHeaders = IdentifierHeaders(prefs)
                for ((key, value) in identifierHeaders.get()) {
                    requestBuilder.addHeader(key, value)
                }
                chain.proceed(requestBuilder.build())
            }
            .addInterceptor(interceptor)
            .build()

        FlippingToolApp.logis.inject(this)

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }


    override fun getRecommendations(): Observable<Response<List<Recommendation>>> {
        return apiService.getRecommendations()
    }

}
