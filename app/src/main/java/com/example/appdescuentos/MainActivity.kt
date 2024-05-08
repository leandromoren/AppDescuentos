package com.example.appdescuentos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import com.example.appdescuentos.ui.theme.AppDescuentosTheme
import com.example.appdescuentos.viewModel.CalcularViewModel1
import com.example.appdescuentos.viewModel.CalcularViewModel2
import com.example.appdescuentos.viewModel.CalcularViewModel3
import com.example.appdescuentos.views.HomeView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: CalcularViewModel1 by viewModels()
        val viewModel2: CalcularViewModel2 by viewModels()
        val viewModel3: CalcularViewModel3 by viewModels()
        setContent {
            AppDescuentosTheme {
                // A surface container using the 'background' color from the theme
                Surface() {
                    HomeView(viewModel3)
                }
            }
        }
    }
}

