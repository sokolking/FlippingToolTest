package nl.vanzanden.flippingtooltest.data.network

import com.google.gson.annotations.SerializedName

open class Response<out T>(

    @field:SerializedName("success")
    val success: Boolean? = null,

    @SerializedName("data")
    val data: T? = null,

    @field:SerializedName("categoryId")
    val categoryId: Int? = null

)