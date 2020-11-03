package nl.vanzanden.flippingtooltest.presenter.news

import io.reactivex.rxkotlin.subscribeBy
import nl.vanzanden.flippingtooltest.app.FlippingToolApp
import nl.vanzanden.flippingtooltest.data.repository.impl.Repository
import nl.vanzanden.flippingtooltest.presenter.BasePresenterImpl
import nl.vanzanden.flippingtooltest.util.defaultObservable
import javax.inject.Inject

class RecommendationNewsPresenter : BasePresenterImpl<IRecommendationNewsPresenter.View>(),
    IRecommendationNewsPresenter.Presenter {

    @Inject
    lateinit var repository: Repository

    init {
        FlippingToolApp.logis.inject(this)
    }

    override fun getRecommendationsNews(id: Int) {
        repository.getRecommendationNews(id)
            .defaultObservable()
            .subscribeBy(
                onError = { mView?.showError(it) },
                onNext = {
                    mView?.getRecommendationsNewsSuccess(it)
                }
            )
    }

}
