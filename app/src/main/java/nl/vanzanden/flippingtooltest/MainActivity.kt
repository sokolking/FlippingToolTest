package nl.vanzanden.flippingtooltest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import nl.vanzanden.flippingtooltest.app.FlippingToolApp
import nl.vanzanden.flippingtooltest.domain.entities.Recommendation
import nl.vanzanden.flippingtooltest.presenter.recommendation.IRecommendationPresenter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), IRecommendationPresenter.View {

    @Inject
    lateinit var recommendationPresenter: IRecommendationPresenter.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FlippingToolApp.logis.inject(this)
        recommendationPresenter.getRecommendations()
    }

    override fun onResume() {
        super.onResume()
        recommendationPresenter.attachView(this)
    }

    override fun onPause() {
        super.onPause()
        recommendationPresenter.detachView()
    }

    override fun getRecommendationsSuccess(recommendations: List<Recommendation>?) {

    }
}
