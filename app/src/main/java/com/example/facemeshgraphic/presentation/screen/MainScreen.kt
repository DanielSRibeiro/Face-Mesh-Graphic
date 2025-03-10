package com.example.facemeshgraphic.presentation.screen

import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.facemeshgraphic.data.FaceMeshAnalyzer
import com.example.facemeshgraphic.presentation.screen.components.CameraPreview
import com.example.facemeshgraphic.presentation.screen.components.DrawCanvas
import com.example.facemeshgraphic.presentation.screen.state.MainState

@Composable
fun MainScreen(
    navController: NavController,
    uiState: MainState
) {
    val lifecycle = LocalLifecycleOwner.current
    var faceMeshModel = uiState.faceMeshModel
    val analyzer = uiState.analyzer

    val controller = remember {
        LifecycleCameraController(navController.context).apply {
            setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
            setImageAnalysisAnalyzer(
                ContextCompat.getMainExecutor(navController.context),
                analyzer
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CameraPreview(
            modifier = Modifier.fillMaxSize(),
            controller = controller,
            lifecycle = lifecycle,
        )

        if (faceMeshModel.faceList.isNotEmpty()) {
            DrawCanvas(
                modifier = Modifier,
                navController = navController,
                faceMeshModel = faceMeshModel
            )
        }
    }
}