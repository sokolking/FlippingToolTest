package nl.vanzanden.flippingtooltest.domain.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RecommendationNews(

    @field:SerializedName("TotalPageCount")
    val totalPageCount: Int? = null,

    @field:SerializedName("BaseImageUrl")
    val baseImageUrl: String? = null

) : Serializable, BaseEntity()