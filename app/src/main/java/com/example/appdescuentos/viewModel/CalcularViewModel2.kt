package com.example.appdescuentos.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalcularViewModel2: ViewModel() {
    // Declaro las variables como privadas
    var precio by mutableStateOf("")
        private set

    var descuento by mutableStateOf("")
        private set

    var precioDescuento by mutableStateOf(0.0)
        private set

    var totalDescuento by mutableStateOf(0.0)
        private set

    var showAlert by mutableStateOf(false)
        private set

    fun onValue(value: String, text: String) {
        when(text) {
            "precio" -> precio = value
            "descuento" -> descuento = value
        }
    }

    fun calcular() {
        if(precio != "" && descuento != "") {
            precioDescuento = calcularPrecio(precio.toDouble(), descuento.toDouble())
            totalDescuento = calcularDescuento(precio.toDouble(), descuento.toDouble())
        }
        else {
            showAlert = true
        }
    }

    private fun calcularPrecio(precio: Double, descuento: Double): Double {
        val res = precio - calcularDescuento(precio, descuento)
        return kotlin.math.round(res * 100 / 100.0)
    }

    private fun calcularDescuento(precio: Double, descuento: Double): Double {
        val res = precio * (1 - descuento / 100)
        return kotlin.math.round(res * 100 / 100.0)
    }

    fun resetear() {
        precio = ""
        descuento = ""
        totalDescuento = 0.0
        precioDescuento = 0.0
    }

    fun cancelAlert() {
        showAlert = false
    }
}