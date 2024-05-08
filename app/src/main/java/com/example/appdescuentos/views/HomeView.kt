package com.example.appdescuentos.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.appdescuentos.components.Alert
import com.example.appdescuentos.components.MainButton
import com.example.appdescuentos.components.MainTextField
import com.example.appdescuentos.components.SpaceH
import com.example.appdescuentos.components.TwoCards
import com.example.appdescuentos.viewModel.CalcularViewModel3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(viewModel3: CalcularViewModel3) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = "Descuentos", color = Color.White)},
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )
    }) {
      HomeViewContent(it, viewModel3)
    }
}

@Composable
fun HomeViewContent(paddingValues: PaddingValues, viewModel3: CalcularViewModel3) {
    Column(modifier = Modifier
        .padding(paddingValues)
        .padding(10.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val state = viewModel3.state

        TwoCards(
            title1 = "Total",
            number1 = state.totalDescuento,
            title2 = "Descuento",
            number2 = state.precioDescuento
        )
        MainTextField(
            value = state.precio,
            onValueChange =  { viewModel3.onValue(it, "precio")},
            label = "Precio $"
        )
        SpaceH()
        MainTextField(
            value = state.descuento,
            onValueChange =  { viewModel3.onValue(it, "descuento")},
            label = "Descuento %"
        )
        SpaceH(10.dp)
        MainButton(text = "Generar descuento") {
            viewModel3.calcular()
        }
        SpaceH()
        MainButton(text = "Resetear", color = Color.Red) {
            viewModel3.resetear()
        }
        if (state.showAlert) {
            Alert(
                onDismissRequest = { /*TODO*/ },
                onConfirmation = {viewModel3.cancelAlert()},
                dialogTitle = "Alerta",
                dialogText = "Ningun campo puede estar vacio."
            )
        }
    }
}
