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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.appdescuentos.components.Alert
import com.example.appdescuentos.components.MainButton
import com.example.appdescuentos.components.MainTextField
import com.example.appdescuentos.components.SpaceH
import com.example.appdescuentos.components.TwoCards

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView() {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = "Descuentos", color = Color.White)},
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )
    }) {
      HomeViewContent(it)
    }
}

@Composable
fun HomeViewContent(paddingValues: PaddingValues) {
    Column(modifier = Modifier
        .padding(paddingValues)
        .padding(10.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var precio by remember { mutableStateOf("") }
        var descuento by remember { mutableStateOf("") }
        var precioDescuento by remember { mutableDoubleStateOf(0.0) }
        var totalDescuento by remember { mutableDoubleStateOf(0.0) }
        var showAlert by remember { mutableStateOf(false) }

        TwoCards(
            title1 = "Total",
            number1 = totalDescuento,
            title2 = "Descuento",
            number2 = precioDescuento
        )
        MainTextField(
            value = precio,
            onValueChange =  { precio = it},
            label = "Precio $"
        )
        SpaceH()
        MainTextField(
            value = descuento,
            onValueChange =  { descuento = it},
            label = "Descuento %"
        )
        SpaceH(10.dp)
        MainButton(text = "Generar descuento") {
            if(precio != "" && descuento != "") {
                precioDescuento = calcularPrecio(precio.toDouble(), descuento.toDouble())
                totalDescuento = calcularDescuento(precio.toDouble(), descuento.toDouble())
            } else {
                showAlert = true
            }
        }
        SpaceH()
        MainButton(text = "Resetear", color = Color.Red) {
            precio = ""
            descuento = ""
            precioDescuento = 0.0
            totalDescuento = 0.0
        }
        if (showAlert) {
            Alert(
                onDismissRequest = { /*TODO*/ },
                onConfirmation = {showAlert = false},
                dialogTitle = "Alerta",
                dialogText = "Ningun campo puede estar vacio."
            )
        }
    }
}

fun calcularPrecio(precio: Double, descuento: Double): Double {
    val res = precio - calcularDescuento(precio, descuento)
    return kotlin.math.round(res * 100 / 100.0)
}

fun calcularDescuento(precio: Double, descuento: Double): Double {
    val res = precio * (1 - descuento / 100)
    return kotlin.math.round(res * 100 / 100.0)
}