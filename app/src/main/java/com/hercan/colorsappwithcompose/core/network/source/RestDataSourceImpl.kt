package com.hercan.colorsappwithcompose.core.network.source

import com.hercan.colorsappwithcompose.core.network.dto.ColorResponseModel
import retrofit2.Response
import javax.inject.Inject

class RestDataSourceImpl @Inject constructor(private val restApi: ColorsRestApi) : RestDataSource {
    override suspend fun getAllColors(): Response<ColorResponseModel> {
        return restApi.getAllColors()
    }
}