package nl.vanzanden.flippingtooltest.presenter.recommendation

import nl.vanzanden.flippingtooltest.domain.entities.Recommendation
import nl.vanzanden.flippingtooltest.presenter.IBasePresenter
import nl.vanzanden.flippingtooltest.presenter.IBaseView

object IRecommendationPresenter {
    interface View : IBaseView {
        fun getRecommendationsSuccess(recommendations: List<Recommendation>?)
    }

    interface Presenter : IBasePresenter<View> {
        fun getRecommendations()
    }
}
