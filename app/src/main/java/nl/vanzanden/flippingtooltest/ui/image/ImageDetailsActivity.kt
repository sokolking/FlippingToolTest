package nl.vanzanden.flippingtooltest.ui.image

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_image_details.*
import nl.vanzanden.flippingtooltest.BuildConfig
import nl.vanzanden.flippingtooltest.R
import nl.vanzanden.flippingtooltest.app.FlippingToolApp
import nl.vanzanden.flippingtooltest.domain.entities.Recommendation
import nl.vanzanden.flippingtooltest.domain.entities.RecommendationNews
import nl.vanzanden.flippingtooltest.presenter.news.IRecommendationNewsPresenter
import nl.vanzanden.flippingtooltest.util.toGone
import javax.inject.Inject

/**
 * Created by alan on 03/11/2020.
 */
class ImageDetailsActivity : AppCompatActivity(), IRecommendationNewsPresenter.View {

    @Inject
    lateinit var recommendationNewsPresenter: IRecommendationNewsPresenter.Presenter

    private lateinit var recommendation: Recommendation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_details)
        FlippingToolApp.logis.inject(this)

        recommendation = intent.getSerializableExtra(KEY_EXTRA_RECOMMENDATION) as Recommendation
        recommendationNewsPresenter.getRecommendationsNews(recommendation.newsId)
    }

    override fun onResume() {
        super.onResume()
        recommendationNewsPresenter.attachView(this)
    }

    override fun onPause() {
        super.onPause()
        recommendationNewsPresenter.detachView()
    }

    override fun getRecommendationsNewsSuccess(recommendationNews: RecommendationNews) {
        initView(recommendationNews)
    }

    private fun initView(recommendationNews: RecommendationNews) {
        val data = List(recommendationNews.totalPageCount ?: 0)
        { "${BuildConfig.STATIC_URL}/${recommendationNews.baseImageUrl}/0/${it + 1}.jpg" }
        pagerImages.adapter = ZoomableImagesPagerAdapter(this, data)
        if (recommendationNews.totalPageCount ?: 0 > 1) {
            circleIndicator.visibility = View.VISIBLE
            circleIndicator.setViewPager(pagerImages)
        } else {
            circleIndicator.visibility = View.GONE
        }
        close.setOnClickListener {
            onBackPressed()
        }
        progress.toGone()
    }

    companion object {
        const val KEY_EXTRA_RECOMMENDATION = "key_extra_recommendation"
    }

}