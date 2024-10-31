package com.hercan.colorsappwithcompose.core.network.source

import com.hercan.colorsappwithcompose.core.network.dto.ColorResponseModel
import retrofit2.Response

interface RestDataSource {
    suspend fun getAllColors(): Response<ColorResponseModel>
}