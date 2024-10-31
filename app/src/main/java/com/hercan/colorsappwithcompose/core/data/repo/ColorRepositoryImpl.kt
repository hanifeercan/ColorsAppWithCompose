package com.hercan.colorsappwithcompose.core.data.repo

import com.hercan.colorsappwithcompose.core.common.ResponseState
import com.hercan.colorsappwithcompose.core.data.model.Color
import com.hercan.colorsappwithcompose.core.network.source.RestDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ColorRepositoryImpl @Inject constructor(private val restDataSource: RestDataSource) :
    ColorRepository {
    override suspend fun getAllColors(): Flow<ResponseState<List<Color>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getAllColors()
            emit(ResponseState.Success(response.mapTo { it.toColorList() }))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }
}