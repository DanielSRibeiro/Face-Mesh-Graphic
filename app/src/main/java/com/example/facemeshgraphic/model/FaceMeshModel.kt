package com.example.facemeshgraphic.model

import com.google.mlkit.vision.facemesh.FaceMesh

data class FaceMeshModel(
    val faceList: List<FaceMesh> = emptyList(),
    val width: Int?,
    val height: Int?,
    val rotationDegrees: Int = 0
)