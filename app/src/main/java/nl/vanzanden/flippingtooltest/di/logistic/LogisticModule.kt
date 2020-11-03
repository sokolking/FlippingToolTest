package nl.vanzanden.flippingtooltest.di.logistic

import dagger.Module
import dagger.Provides
import nl.vanzanden.flippingtooltest.presenter.news.IRecommendationNewsPresenter
import nl.vanzanden.flippingtooltest.presenter.news.RecommendationNewsPresenter
import nl.vanzanden.flippingtooltest.presenter.recommendation.IRecommendationPresenter
import nl.vanzanden.flippingtooltest.presenter.recommendation.RecommendationPresenter

@Module
class LogisticModule {

    @Provides
    @LogisticScope
    fun provideRecommendationPresenter(): IRecommendationPresenter.Presenter {
        return RecommendationPresenter()
    }

    @Provides
    @LogisticScope
    fun provideRecommendationNewsPresenter(): IRecommendationNewsPresenter.Presenter {
        return RecommendationNewsPresenter()
    }

}
