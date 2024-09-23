package com.andrews.videoplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.andrews.videoplayer.ui.navigation.NavGraph
import com.andrews.videoplayer.ui.theme.VideoPlayerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        
        setContent {
            val navController = rememberNavController()
            VideoPlayerTheme {
                NavGraph(navController = navController)
            }
        }
    }
}