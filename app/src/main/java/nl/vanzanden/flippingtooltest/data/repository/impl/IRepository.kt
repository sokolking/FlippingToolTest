package nl.vanzanden.flippingtooltest.data.repository.impl

import io.reactivex.Observable
import nl.vanzanden.flippingtooltest.data.network.Response
import nl.vanzanden.flippingtooltest.domain.entities.Recommendation

interface IRepository {

    fun getRecommendations(): Observable<Response<List<Recommendation>>>

}


