package nl.vanzanden.flippingtooltest.ui.image

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.viewpager.widget.PagerAdapter
import nl.vanzanden.flippingtooltest.R
import nl.vanzanden.flippingtooltest.util.loadImageFromUrl

/**
 * Created by alan on 03/11/2020.
 */
class ZoomableImagesPagerAdapter(
    context: Context,
    private val imageUrls: List<String>
) : PagerAdapter() {

    private val inflater = LayoutInflater.from(context)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return (inflater.inflate(
            R.layout.item_pager_image_zoomable,
            container,
            false
        ) as AppCompatImageView).apply {
            loadImageFromUrl(imageUrls[position], imageUrls[position].replace("/0/", "/1/"))
            container.addView(this)
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount() = imageUrls.size
}