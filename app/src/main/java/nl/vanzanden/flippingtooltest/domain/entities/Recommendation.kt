package nl.vanzanden.flippingtooltest.domain.entities

import com.google.gson.annotations.SerializedName
import nl.vanzanden.flippingtooltest.BuildConfig
import java.io.Serializable

data class Recommendation(

    @field:SerializedName("ID")
    val id: Int? = null,

    @field:SerializedName("Name")
    val name: String? = null,

    @field:SerializedName("LogoPath")
    val logoPath: String? = null

) : Serializable, BaseEntity() {
    fun getLogoUrl() = BuildConfig.STATIC_URL + logoPath
}