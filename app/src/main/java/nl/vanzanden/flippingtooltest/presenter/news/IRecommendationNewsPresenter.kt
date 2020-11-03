package nl.vanzanden.flippingtooltest.presenter.news

import nl.vanzanden.flippingtooltest.domain.entities.RecommendationNews
import nl.vanzanden.flippingtooltest.presenter.IBasePresenter
import nl.vanzanden.flippingtooltest.presenter.IBaseView

object IRecommendationNewsPresenter {
    interface View : IBaseView {
        fun getRecommendationsNewsSuccess(recommendationNews: RecommendationNews)
    }

    interface Presenter : IBasePresenter<View> {
        fun getRecommendationsNews(id: Int)
    }
}
