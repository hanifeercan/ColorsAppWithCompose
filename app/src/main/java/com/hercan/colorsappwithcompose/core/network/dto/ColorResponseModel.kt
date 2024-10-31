package com.hercan.colorsappwithcompose.core.network.dto

import com.google.gson.annotations.SerializedName

data class ColorResponseModel(
    @SerializedName("colors") val colors: List<Color?>?,
    @SerializedName("count") val count: Int?,
    @SerializedName("message") val message: String?,
    @SerializedName("status") val status: Int?,
    @SerializedName("statusText") val statusText: String?
) {
    data class Color(
        @SerializedName("group") val group: String?,
        @SerializedName("hex") val hex: String?,
        @SerializedName("name") val name: String?,
        @SerializedName("rgb") val rgb: String?,
        @SerializedName("theme") val theme: String?
    )
}