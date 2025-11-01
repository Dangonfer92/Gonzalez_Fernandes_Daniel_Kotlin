package com.example.gonzalez_fernandes_daniel_kotlin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import java.lang.Math.sqrt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        /**ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        insets
        }*/

        val entradaNumero = findViewById<EditText>(R.id.entradaNumero)
        val botonCalcular = findViewById<Button>(R.id.botonCalcular)
        val etiquetaResultado = findViewById<TextView>(R.id.etiquetaResultado)

        botonCalcular.setOnClickListener {
            val textoUsuario = entradaNumero.text.toString().trim()
            if (textoUsuario.isEmpty()) {
                mostrarMensaje("Por favor, introduce un número positivo.")
                return@setOnClickListener
            }
            try {
                val numero = textoUsuario.toInt()
                if (numero <= 0) {
                    mostrarMensaje("El número debe ser positivo.")
                    return@setOnClickListener
                }
                // Calcular primos menores a numero
                val primos = obtenerPrimosMenores(numero)
                etiquetaResultado.setTextColor(getColor(android.R.color.black))
                etiquetaResultado.text = primos.joinToString(", ")
            } catch (e: NumberFormatException) {
                this.mostrarMensaje("El dato introducido no es un número válido.")
            } catch (e: Exception) {
                mostrarMensaje("Error inesperado: ${e.message}")
            }
        }
    }

    private fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    private fun esPrimo(n: Int): Boolean {    // Ejercicio de JuanCarlos Tema 3
        if (n < 2) return false
        for (i in 2..sqrt(n.toDouble()).toInt()) {
            if (n % i == 0) return false
        }
        return true
    }


    private fun obtenerPrimosMenores(limite: Int): List<Int> {
        val listaPrimos = mutableListOf<Int>()
        for (num in 2 until limite) {
            if (esPrimo(num)) listaPrimos.add(num)
        }
        return listaPrimos
    }
}