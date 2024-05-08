package com.example.appdescuentos.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.appdescuentos.model.CalcularState

class CalcularViewModel3: ViewModel() {
    // Traigo todas las variables en una sola linea de codigo
    var state by mutableStateOf(CalcularState())
        private set

    fun onValue(value: String, text: String) {
        when(text) {
            "precio" -> state = state.copy(precio = value)
            "descuento" -> state = state.copy(descuento = value)
        }
    }

    fun calcular() {
        if(state.precio != "" && state.descuento != "") {
            state = state.copy(
                precioDescuento = calcularPrecio(state.precio.toDouble(), state.descuento.toDouble()),
                totalDescuento = calcularDescuento(state.precio.toDouble(), state.descuento.toDouble())
            )
        }
        else {
            state = state.copy(
                showAlert = true
            )
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
        state = state.copy(
            precio = "" ,
            descuento = "",
            totalDescuento = 0.0,
            precioDescuento = 0.0
        )
    }

    fun cancelAlert() {
        state = state.copy(
            showAlert = false
        )
    }
}