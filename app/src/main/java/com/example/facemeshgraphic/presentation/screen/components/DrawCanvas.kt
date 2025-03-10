package com.example.facemeshgraphic.presentation.screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.navigation.NavController
import com.example.facemeshgraphic.model.FaceMeshModel

@Composable
fun DrawCanvas(
    modifier: Modifier = Modifier,
    navController: NavController,
    faceMeshModel: FaceMeshModel
) {
    val displayMetrics = navController.context.resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels
    val screenHeight = displayMetrics.heightPixels

    val canvasWidth = faceMeshModel.width!!.toFloat()
    val canvasHeight = faceMeshModel.height!!.toFloat()

    val scaleX = screenWidth.toFloat() / canvasWidth
    val scaleY = screenHeight.toFloat() / canvasHeight

    var scale = Math.min(scaleX, scaleY)
    scale *= 2.1f
    scale = Math.min(scale, Math.max(scaleX, scaleY))

    val offsetX = (screenWidth - (canvasWidth * scale)) / 2f

    Canvas(
        modifier = modifier,
        onDraw = {
            faceMeshModel.faceList.forEach { faceMesh ->
                val faceMeshPoints = faceMesh.allPoints
                faceMeshPoints.forEach { faceMeshPoint ->
                    val position = faceMeshPoint.position
                    val scaledY = position.y * scale
                    val mirroredX = screenWidth - (position.x * scale + (offsetX/2))

                    drawPoints(
                        points = listOf(Offset(mirroredX, scaledY-10)),
                        pointMode = PointMode.Points,
                        color = Color.Red,
                        strokeWidth = 10f
                    )
                    drawPoints(
                        points = listOf(Offset(screenWidth - position.x, position.y)),
                        pointMode = PointMode.Points,
                        color = Color.Red,
                        strokeWidth = 10f
                    )
                }
            }
        }
    )
}
