package com.example.schoolhelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.schoolhelper.ui.BottomBar
import com.example.schoolhelper.ui.NavigationGraph
import com.example.schoolhelper.ui.theme.SchoolHelperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolHelperTheme {
                val navController = rememberNavController()
                Scaffold(bottomBar = { BottomBar(navController = navController) }) {
                    NavigationGraph(navController = navController)
                }
            }
        }
    }
}