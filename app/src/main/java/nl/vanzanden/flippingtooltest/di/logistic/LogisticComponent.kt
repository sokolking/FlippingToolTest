package nl.vanzanden.flippingtooltest.di.logistic

import dagger.Component
import nl.vanzanden.flippingtooltest.data.repository.impl.Repository
import nl.vanzanden.flippingtooltest.data.repository.preferences.PreferenceHelper
import nl.vanzanden.flippingtooltest.di.AppComponent
import nl.vanzanden.flippingtooltest.presenter.news.RecommendationNewsPresenter
import nl.vanzanden.flippingtooltest.presenter.recommendation.RecommendationPresenter
import nl.vanzanden.flippingtooltest.ui.MainActivity
import nl.vanzanden.flippingtooltest.ui.image.ImageDetailsActivity

@Component(dependencies = [AppComponent::class], modules = [(LogisticModule::class)])
@LogisticScope
interface LogisticComponent {

    //other
    fun inject(authRepository: Repository)
    fun inject(preferenceHelper: PreferenceHelper)

    //activity
    fun inject(mainActivity: MainActivity)
    fun inject(imageDetailsActivity: ImageDetailsActivity)

    //presenter
    fun inject(recommendationPresenter: RecommendationPresenter)
    fun inject(recommendationNewsPresenter: RecommendationNewsPresenter)

    //fragment

}
