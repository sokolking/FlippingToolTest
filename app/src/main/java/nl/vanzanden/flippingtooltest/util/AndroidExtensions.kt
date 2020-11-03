package nl.vanzanden.flippingtooltest.util

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import nl.vanzanden.flippingtooltest.R

/**
 * Created by alan on 03/11/2020.
 */

fun ImageView.loadThumbnailUrl(thumbnailUrl: String?): RequestBuilder<Drawable>? {
    if (thumbnailUrl?.isNotEmpty() == true) {
        return Glide.with(context)
            .load(thumbnailUrl)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .thumbnail(0.1f)
            .timeout(30000)
            .error(R.drawable.ic_launcher_background)
            .transition(DrawableTransitionOptions.withCrossFade())
    }
    return null
}

fun ImageView.loadImageFromUrl(imageUrl: String?, thumbnailUrl: String? = "") {
    if (imageUrl?.isNotEmpty() == true) {
        Glide.with(context)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .thumbnail(loadThumbnailUrl(thumbnailUrl))
            .timeout(30000)
            .error(R.drawable.ic_launcher_background)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}

fun View.toVisible() {
    visibility = View.VISIBLE
}

fun View.toEnable() {
    isEnabled = true
}

fun View.toInvisible() {
    visibility = View.INVISIBLE
}

fun View.toGone() {
    visibility = View.GONE
}