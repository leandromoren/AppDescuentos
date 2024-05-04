package com.example.appdescuentos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.appdescuentos.ui.theme.AppDescuentosTheme
import com.example.appdescuentos.views.HomeView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppDescuentosTheme {
                // A surface container using the 'background' color from the theme
                Surface() {
                    HomeView()
                }
            }
        }
    }
}

