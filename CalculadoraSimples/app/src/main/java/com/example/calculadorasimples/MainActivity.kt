package com.example.calculadorasimples

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import com.example.calculadorasimples.logic.adicao
import com.example.calculadorasimples.logic.divisao
import com.example.calculadorasimples.logic.multiplicacao
import com.example.calculadorasimples.logic.subtracao
import com.example.calculadorasimples.ui.theme.CalculadoraSimplesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraSimplesTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    CalculadoraScreen()
                }
            }
        }
    }
}

@Composable
fun CalculadoraScreen() {
    //variaveis locais para armazenar os valores dos campos de texto
    var numero1 by remember { mutableStateOf("") }
    var numero2 by remember { mutableStateOf("") }
    var operacao by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    val historico = remember { mutableStateListOf<String>() }
    //função para calcular o resultado
    fun calcular() {
        val n1 = if(numero1.isEmpty()) 0.0 else numero1.toDoubleOrNull() ?: 0.0
        val n2 = if (numero2.isEmpty()) 0.0 else numero2.toDoubleOrNull() ?: 0.0
        resultado = when (operacao) {
            "+" -> {
                adicao(n1, n2).toString()
            }
            "-" -> {
                subtracao(n1, n2).toString()
            }
            "*" -> {
                multiplicacao(n1, n2).toString()
            }
            "/" -> {if (n2 == 0.0) "Não é possível dividir por zero" else
                divisao(n1, n2).toString()
            }
            else -> {"operação inválida"}
        }

    }
    Column (modifier = Modifier
        .padding(top = 60.dp, start = 16.dp, end = 16.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Calculadora Simples",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Digite o primeiro número:",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold)
        TextField(
            value = numero1,
            onValueChange = { numero1 = it },
            label = { Text("Número 1") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Digite o segundo número:",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold)
        TextField(
            value = numero2,
            onValueChange = { numero2 = it },
            label = { Text("Número 2") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Selecione a operação que deseja realizar:",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(modifier = Modifier.padding(4.dp),
                onClick = { operacao = "+"; calcular(); historico.add(0,"$numero1 + $numero2 = $resultado") }) { Text("+",
                fontWeight = FontWeight.Bold) }
            Button(modifier = Modifier.padding(4.dp),
                onClick = { operacao = "-"; calcular(); historico.add(0,"$numero1 - $numero2 = $resultado") }) { Text("-",
                fontWeight = FontWeight.Bold) }
            Button(modifier = Modifier.padding(4.dp),
                onClick = { operacao = "*"; calcular(); historico.add(0,"$numero1 * $numero2 = $resultado") }) { Text("*",
                fontWeight = FontWeight.Bold) }
            Button(modifier = Modifier.padding(4.dp),
                onClick = { operacao = "/"; calcular(); historico.add(0,"$numero1 / $numero2 = $resultado") }) { Text("/",
                fontWeight = FontWeight.Bold) }
        }
        Row ( modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {

            Button(onClick = { numero1 = ""; numero2 = ""; operacao = ""; resultado = ""; }) {
                Text(
                    "Limpar a tela",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Button(onClick = { historico.clear();}) {
                Text(
                    "Limpar histórico",
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Resultado: $resultado",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Histórico de operações:",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(15.dp))
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp))  {
            items(historico.size) {
                Card(modifier = Modifier.padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(4.dp))   { Text(text = historico[it],
                    modifier = Modifier.padding(8.dp)) }
            }
        }
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalculadoraApp() {
    CalculadoraScreen()
}
