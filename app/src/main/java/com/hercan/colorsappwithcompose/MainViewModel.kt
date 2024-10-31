package com.hercan.colorsappwithcompose

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hercan.colorsappwithcompose.core.common.ResponseState
import com.hercan.colorsappwithcompose.core.domain.GetAllColorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getAllColorsUseCase: GetAllColorsUseCase) :
    ViewModel() {

    private val _colorScreenUiState = MutableStateFlow(ColorListScreenUiState.initial())
    val colorScreenUiState: StateFlow<ColorListScreenUiState> get() = _colorScreenUiState

    init {
        getAllColors()
    }

    fun getAllColors() {
        viewModelScope.launch {
            getAllColorsUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Error -> {
                        _colorScreenUiState.value = ColorListScreenUiState(
                            isError = true, errorMessage = responseState.message
                        )

                    }

                    is ResponseState.Loading -> {
                        _colorScreenUiState.value = ColorListScreenUiState(isLoading = true)
                    }

                    is ResponseState.Success -> {
                        _colorScreenUiState.value = (ColorListScreenUiState(responseState.data))
                        Log.d("discover_data2", responseState.data.toString())
                    }
                }
            }
        }
    }
}