package nl.vanzanden.flippingtooltest.domain.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Recommendation(

    @field:SerializedName("ID")
    val ID: Int? = null,

    @field:SerializedName("Name")
    val Name: String? = null,

    @field:SerializedName("LogoPath")
    val LogoPath: String? = null

) : Serializable, BaseEntity()