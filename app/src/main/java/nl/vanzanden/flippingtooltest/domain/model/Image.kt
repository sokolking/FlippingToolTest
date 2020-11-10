package nl.vanzanden.flippingtooltest.domain.model

import android.graphics.Bitmap

class Image(
    val position: Int = 0,
    var bitmap: Bitmap? = null,
    val url: String? = null,
    var isFullDownloaded: Boolean = false
) {
    fun getFullImageUrl(): String? = url?.replace("/1/", "/0/")
}