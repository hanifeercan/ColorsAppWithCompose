package com.hercan.colorsappwithcompose

import com.hercan.colorsappwithcompose.core.data.model.Color

data class ColorListScreenUiState(
    val colors: List<Color> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = ""
) {
    companion object {
        fun initial() = ColorListScreenUiState(isLoading = true)
    }
}