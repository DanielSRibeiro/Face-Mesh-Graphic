package com.example.facemeshgraphic.presentation.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.facemeshgraphic.model.ResultStatus
import com.example.facemeshgraphic.model.usecase.GetFaceAnalyzerUseCase
import com.example.facemeshgraphic.presentation.screen.state.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFaceAnalyzer: GetFaceAnalyzerUseCase
) : ViewModel() {

    var uiState by mutableStateOf(MainState())
        private set

    init {
        viewModelScope.launch {
            getFaceAnalyzer.invoke(
                params = GetFaceAnalyzerUseCase.Params(
                    onFaceMeshDetected = { faceModel ->
                        uiState = uiState.copy(faceMeshModel = faceModel)

                    }
                )
            ).collectLatest { result ->
                when (result) {
                    is ResultStatus.Failure -> uiState =
                        uiState.copy(isError = true, isLoading = false)

                    ResultStatus.Loading -> uiState =
                        uiState.copy(isError = false, isLoading = true)

                    is ResultStatus.Success -> uiState =
                        uiState.copy(isError = false, isLoading = false, analyzer = result.data)
                }
            }
        }
    }
}