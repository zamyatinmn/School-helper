package com.example.schoolhelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.schoolhelper.ui.FirstScreen
import com.example.schoolhelper.ui.theme.SchoolHelperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolHelperTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    FirstScreen(Modifier.fillMaxSize())
                }
            }
        }
    }
}