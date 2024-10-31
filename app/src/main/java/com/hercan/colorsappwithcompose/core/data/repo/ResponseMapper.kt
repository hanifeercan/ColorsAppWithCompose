package com.hercan.colorsappwithcompose.core.data.repo

import com.hercan.colorsappwithcompose.core.data.model.Color
import com.hercan.colorsappwithcompose.core.network.dto.ColorResponseModel
import retrofit2.Response

typealias RestColorsResponse = Response<ColorResponseModel>

fun RestColorsResponse.toColorList(): List<Color> {
    return if (body()!!.colors.isNullOrEmpty()) {
        emptyList()
    } else {
        body()!!.colors!!.map {
            Color(
                it?.name ?: "", it?.hex ?: ""
            )
        }
    }
}