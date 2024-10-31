package com.hercan.colorsappwithcompose.core.domain

import com.hercan.colorsappwithcompose.core.common.ResponseState
import com.hercan.colorsappwithcompose.core.data.model.Color
import com.hercan.colorsappwithcompose.core.data.repo.ColorRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllColorsUseCase @Inject constructor(private val repository: ColorRepository) {
    suspend operator fun invoke(): Flow<ResponseState<List<Color>>> {
        return repository.getAllColors()
    }
}