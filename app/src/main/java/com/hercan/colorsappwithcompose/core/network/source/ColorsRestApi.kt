package com.hercan.colorsappwithcompose.core.network.source

import com.hercan.colorsappwithcompose.core.network.dto.ColorResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface ColorsRestApi {
    @GET(".")
    suspend fun getAllColors(): Response<ColorResponseModel>
}