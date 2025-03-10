package com.example.facemeshgraphic

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.example.facemeshgraphic.presentation.navigation.NavigationGraph
import com.example.facemeshgraphic.ui.theme.ObjectDetectionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(!requestCameraPermissions()) {
            ActivityCompat.requestPermissions(
                this,
                CAMERA_PERMISSIONS.toTypedArray(),
                0
            )
        }
        setContent {
            ObjectDetectionTheme {
                NavigationGraph(navController = rememberNavController())
            }
        }
    }

    private fun requestCameraPermissions(): Boolean {
        return CAMERA_PERMISSIONS.all { permission ->
            ContextCompat.checkSelfPermission(
                applicationContext,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    companion object {
        private val CAMERA_PERMISSIONS = listOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.RECORD_AUDIO,
        )
    }
}
