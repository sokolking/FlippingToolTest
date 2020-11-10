package nl.vanzanden.flippingtooltest.ui.image

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener
import kotlinx.android.synthetic.main.activity_image_details.*
import nl.vanzanden.flippingtooltest.BuildConfig
import nl.vanzanden.flippingtooltest.R
import nl.vanzanden.flippingtooltest.app.FlippingToolApp
import nl.vanzanden.flippingtooltest.domain.entities.Recommendation
import nl.vanzanden.flippingtooltest.domain.entities.RecommendationNews
import nl.vanzanden.flippingtooltest.domain.model.Image
import nl.vanzanden.flippingtooltest.presenter.news.IRecommendationNewsPresenter
import nl.vanzanden.flippingtooltest.util.getPhoneHeight
import nl.vanzanden.flippingtooltest.util.getPhoneWidth
import javax.inject.Inject

/**
 * Created by alan on 03/11/2020.
 */
class ImageDetailsActivity : AppCompatActivity(), IRecommendationNewsPresenter.View,
    View.OnScrollChangeListener {

    @Inject
    lateinit var recommendationNewsPresenter: IRecommendationNewsPresenter.Presenter

    private lateinit var recommendation: Recommendation
    private lateinit var items: List<String>
    private lateinit var view: Bitmap

    private var loadedImages = arrayListOf<Image>()
    private var lastVisiblePosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_details)
        FlippingToolApp.logis.inject(this)
        scrollView.setOnScrollChangeListener(this)
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
        { "${BuildConfig.STATIC_URL}/${recommendationNews.baseImageUrl}/1/${it + 1}.jpg" }
        this.items = data
        view = createSingleImageFromMultipleImages(data.subList(0, 1).map { getEmptyBitmap() })
        updateView()
        drawView()
    }

    private fun getEmptyBitmap() =
        Bitmap.createBitmap(getPhoneWidth(), getPhoneHeight(), Bitmap.Config.ALPHA_8)

    private fun getBitmapFromURL(url: String?, action: (Bitmap?) -> Unit) {
        ImageLoader.getInstance().loadImage(url, object : SimpleImageLoadingListener() {
            override fun onLoadingComplete(imageUri: String?, view: View?, loadedImage: Bitmap?) {
                action.invoke(loadedImage)
            }
        })
    }

    private fun createSingleImageFromMultipleImages(images: List<Bitmap?>): Bitmap {
        var width = 0F
        val result = Bitmap.createBitmap(
            getPhoneWidth() * images.filterNotNull().size,
            getPhoneHeight(),
            Bitmap.Config.RGB_565
        )
        val canvas = Canvas(result)
        for (element in images) {
            if (element == null) continue
            canvas.drawBitmap(element, width, 0f)
            width += getPhoneWidth()
        }
        return result
    }

    private fun Canvas.drawBitmap(bitmap: Bitmap, left: Float, top: Float) {
        val newBitmap = Bitmap.createScaledBitmap(bitmap, getPhoneWidth(), getPhoneHeight(), false)
        drawBitmap(newBitmap, left, top, null)
    }

    private fun drawView() {
        view = createSingleImageFromMultipleImages((0..lastVisiblePosition).map { index ->
            if (loadedImages.any { it.position == index }) {
                loadedImages[index].bitmap ?: getEmptyBitmap()
            } else {
                if (index < items.size) getEmptyBitmap()
                else null
            }
        })
        updateImageView()
    }

    private fun updateView() {
        if (lastVisiblePosition + 1 > items.size) return
        if (!loadedImages.any { it.position == lastVisiblePosition }) {
            val element = Image(
                lastVisiblePosition, null,
                items[lastVisiblePosition], false
            )
            getBitmapFromURL(element.url) {
                element.bitmap = it
                loadedImages.add(element)
                drawView()
                if (!element.isFullDownloaded) {
                    getBitmapFromURL(element.getFullImageUrl()) {
                        element.bitmap = it
                        element.isFullDownloaded = true
                        loadedImages[lastVisiblePosition] = element
                        lastVisiblePosition++
                        drawView()
                    }
                }
            }
        }

    }

    private fun updateImageView() {
        imageView.setImageBitmap(view)
        imageView.invalidate()
    }

    override fun onScrollChange(
        v: View?,
        scrollX: Int,
        scrollY: Int,
        oldScrollX: Int,
        oldScrollY: Int
    ) {
        if (scrollX / getPhoneWidth() >= lastVisiblePosition) {
            updateView()
        }
    }

    companion object {
        const val KEY_EXTRA_RECOMMENDATION = "key_extra_recommendation"
    }

}