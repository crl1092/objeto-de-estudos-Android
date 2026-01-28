package com.example.imc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imc.ui.theme.ImcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImcTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ImcApp()
                }
            }
        }
    }
}
@Composable
fun ImcInput (
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    value: String,
    label: String,
    isUltimo: Boolean = false

) {
    Row (modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = if (isUltimo) ImeAction.Done else ImeAction.Next

            ),

            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
    }
}
@Composable
fun ImcApp (modifier: Modifier = Modifier) {
    Column(modifier
        .padding(8.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        var peso by remember { mutableStateOf("") }
        var altura by remember { mutableStateOf("") }
        var imc by remember { mutableStateOf(0.0) }
        var classificacao by remember { mutableStateOf("") }

        fun calcularImc() {
            val pesoDouble = if(peso.isEmpty()) 0.0 else peso.toDoubleOrNull()
            val alturaDouble = if (altura.isEmpty()) 0.0 else altura.toDoubleOrNull()

            if (pesoDouble != null && alturaDouble != null && alturaDouble > 0) {
                imc = pesoDouble / (alturaDouble * alturaDouble)
                classificacao = when {
                    imc < 18.5 -> "Abaixo do peso"
                    imc < 25 -> "Peso normal"
                    imc < 30 -> "Sobrepeso"
                    imc < 35 -> "Obesidade Grau I"
                    imc < 40 -> "Obesidade Grau II"
                    else -> "Obesidade Grau III ou Mórbida"
                }
            }
        }

        Text(text = "Calculadora de IMC",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Digite seu peso e altura nos campos abaixo para calcular o IMC",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface)
        Spacer(modifier = Modifier.height(16.dp))
        ImcInput(
            value = peso,
            onValueChange = { peso = it },
            label = "Peso",

        )
        Spacer(modifier = Modifier.height(16.dp))
        ImcInput(
            value = altura,
            onValueChange = { altura = it },
            label = "Altura",
            isUltimo = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { calcularImc() }) {

            Text("Calcular Imc",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.background,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (imc > 0.0) {
            Text(text = "Seu IMC é: ${String.format("%.2f", imc)} \n Classificação: $classificacao",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImcTheme {
       ImcApp()
    }
}
