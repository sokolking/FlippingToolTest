package nl.vanzanden.flippingtooltest.di.logistic

import dagger.Module
import dagger.Provides
import nl.vanzanden.flippingtooltest.presenter.recommendation.IRecommendationPresenter
import nl.vanzanden.flippingtooltest.presenter.recommendation.RecommendationPresenter

@Module
class LogisticModule {

    @Provides
    @LogisticScope
    fun provideCategoriesPresenter(): IRecommendationPresenter.Presenter {
        return RecommendationPresenter()
    }

}
