package nl.vanzanden.flippingtooltest.util

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import nl.vanzanden.flippingtooltest.R

/**
 * Created by alan on 03/11/2020.
 */

fun ImageView.loadThumbnailUrl(
    thumbnailUrl: String? = "",
    centerCrop: Boolean = false,
    cornersRadiusDp: Int = 0
): RequestBuilder<Drawable> {
    return Glide.with(context)
        .load(thumbnailUrl)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .thumbnail(0.1f)
        .timeout(30000)
        .error(R.drawable.ic_launcher_background)
        .transform(MultiTransformation(CenterCrop()))
        .applyTransformations(centerCrop, cornersRadiusDp)
        .transition(DrawableTransitionOptions.withCrossFade())
}

fun ImageView.loadImageFromUrl(
    imageUrl: String?,
    thumbnailUrl: String? = "",
    centerCrop: Boolean = false,
    cornersRadiusDp: Int = 0,
    action: (() -> Unit?)? = null
) {
    if (imageUrl?.isNotEmpty() == true) {
        Glide.with(context)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .thumbnail(loadThumbnailUrl(thumbnailUrl))
            .timeout(30000)
            .error(R.drawable.ic_launcher_background)
            .transform(MultiTransformation(CenterCrop()))
            .applyTransformations(centerCrop, cornersRadiusDp)
            .transition(DrawableTransitionOptions.withCrossFade())
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    isFirstResource: Boolean
                ): Boolean {
                    action?.invoke()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    action?.invoke()
                    return false
                }
            })
            .into(this)
    }
}

fun RequestBuilder<Drawable>.applyTransformations(
    centerCrop: Boolean = false,
    cornersRadiusDp: Int = 0
): RequestBuilder<Drawable> {
    val transformation = when {
        cornersRadiusDp > 0 && centerCrop -> MultiTransformation(
            CenterCrop(),
            RoundedCorners(cornersRadiusDp)
        )
        centerCrop -> CenterCrop()
        cornersRadiusDp > 0 -> RoundedCorners(cornersRadiusDp)
        else -> null
    }
    return apply(transformation?.let { RequestOptions().transform(it) } ?: RequestOptions())
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