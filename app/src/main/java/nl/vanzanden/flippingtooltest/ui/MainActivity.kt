package nl.vanzanden.flippingtooltest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import nl.vanzanden.flippingtooltest.R
import nl.vanzanden.flippingtooltest.app.FlippingToolApp
import nl.vanzanden.flippingtooltest.domain.entities.Recommendation
import nl.vanzanden.flippingtooltest.presenter.recommendation.IRecommendationPresenter
import nl.vanzanden.flippingtooltest.ui.recommendations.RecommendationsAdapter
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
        recommendations?.let {
            rvRecommendations.apply {
                adapter = RecommendationsAdapter(it) { category ->

                }
            }
        }
    }
}
