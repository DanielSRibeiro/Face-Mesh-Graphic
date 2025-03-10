package com.example.facemeshgraphic.presentation.screen.state

import com.example.facemeshgraphic.data.FaceMeshAnalyzer
import com.example.facemeshgraphic.model.FaceMeshModel

data class MainState(
    var faceMeshModel: FaceMeshModel =
        FaceMeshModel(
            faceList = emptyList(),
            width = null,
            height = null
        ),
    var analyzer: FaceMeshAnalyzer? = null,
    var isLoading: Boolean = false,
    var isError: Boolean = false
)
