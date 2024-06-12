package com.example.calculadora_kotlin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadora_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonSumar.setOnClickListener {
            realizarOperacion("sumar")
        }

        binding.botonRestar.setOnClickListener {
            realizarOperacion("restar")
        }

        binding.botonMultiplicar.setOnClickListener {
            realizarOperacion("multiplicar")
        }

        binding.botonDividir.setOnClickListener {
            realizarOperacion("dividir")
        }

        binding.botonBorrar.setOnClickListener {
            borrarCampos()
        }
    }

    private fun borrarCampos() {
        binding.Numero1.text.clear()
        binding.Numero2.text.clear()
        binding.Resultado.text = ""
    }

    private fun realizarOperacion(operacion: String) {
        val num1String = binding.Numero1.text.toString()
        val num2String = binding.Numero2.text.toString()

        if (num1String.isNotEmpty() && num2String.isNotEmpty()) {
            val num1 = num1String.toFloatOrNull()
            val num2 = num2String.toFloatOrNull()

            if (num1 != null && num2 != null) {
                val resultado = when (operacion) {
                    "sumar" -> num1 + num2
                    "restar" -> num1 - num2
                    "multiplicar" -> num1 * num2
                    "dividir" -> {
                        if (num2 != 0f) num1 / num2 else {
                            Toast.makeText(
                                this,
                                "División por cero no permitida.",
                                Toast.LENGTH_SHORT
                            ).show()
                            return
                        }
                    }

                    else -> 0f
                }
                binding.Resultado.text = String.format("Resultado: %.2f", resultado)
            }
        }
    }
}