package nl.vanzanden.flippingtooltest.di.logistic

import dagger.Component
import nl.vanzanden.flippingtooltest.ui.MainActivity
import nl.vanzanden.flippingtooltest.data.repository.impl.Repository
import nl.vanzanden.flippingtooltest.data.repository.preferences.PreferenceHelper
import nl.vanzanden.flippingtooltest.di.AppComponent
import nl.vanzanden.flippingtooltest.presenter.recommendation.RecommendationPresenter

@Component(dependencies = [AppComponent::class], modules = [(LogisticModule::class)])
@LogisticScope
interface LogisticComponent {

    //other
    fun inject(authRepository: Repository)
    fun inject(preferenceHelper: PreferenceHelper)

    //activity
    fun inject(mainActivity: MainActivity)

    //presenter
    fun inject(recommendationPresenter: RecommendationPresenter)

    //fragment

}
