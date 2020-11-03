package nl.vanzanden.flippingtooltest.data.api

import io.reactivex.Observable
import nl.vanzanden.flippingtooltest.data.network.Response
import nl.vanzanden.flippingtooltest.domain.entities.Recommendation
import retrofit2.http.GET

interface ApiService {

    @GET("Shared/GetRecommendationPublishers")
    fun getRecommendations(): Observable<Response<List<Recommendation>>>

}
