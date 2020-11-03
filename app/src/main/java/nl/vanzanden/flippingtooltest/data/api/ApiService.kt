package nl.vanzanden.flippingtooltest.data.api

import io.reactivex.Observable
import nl.vanzanden.flippingtooltest.data.network.Response
import nl.vanzanden.flippingtooltest.domain.entities.Recommendation
import nl.vanzanden.flippingtooltest.domain.entities.RecommendationNews
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("Shared/GetRecommendationPublishers")
    fun getRecommendations(): Observable<Response<List<Recommendation>>>

    @GET("Api/Media/GetNewsMedia")
    fun getRecommendationNews(@Query("newsId") newsId: Int): Observable<RecommendationNews>

}
