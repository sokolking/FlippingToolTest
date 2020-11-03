package nl.vanzanden.flippingtooltest.presenter.recommendation

import io.reactivex.rxkotlin.subscribeBy
import nl.vanzanden.flippingtooltest.app.FlippingToolApp
import nl.vanzanden.flippingtooltest.data.repository.impl.Repository
import nl.vanzanden.flippingtooltest.presenter.BasePresenterImpl
import nl.vanzanden.flippingtooltest.util.defaultObservable
import javax.inject.Inject

class RecommendationPresenter : BasePresenterImpl<IRecommendationPresenter.View>(),
    IRecommendationPresenter.Presenter {

    @Inject
    lateinit var repository: Repository

    init {
        FlippingToolApp.logis.inject(this)
    }

    override fun getRecommendations() {
        repository.getRecommendations()
            .defaultObservable()
            .subscribeBy(
                onError = { mView?.showError(it) },
                onNext = {
                    mView?.getRecommendationsSuccess(it.data)
                }
            )
    }
}
