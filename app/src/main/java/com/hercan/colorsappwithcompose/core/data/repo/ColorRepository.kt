package com.hercan.colorsappwithcompose.core.data.repo

import com.hercan.colorsappwithcompose.core.common.ResponseState
import com.hercan.colorsappwithcompose.core.data.model.Color
import kotlinx.coroutines.flow.Flow

interface ColorRepository {
    suspend fun getAllColors(): Flow<ResponseState<List<Color>>>
}