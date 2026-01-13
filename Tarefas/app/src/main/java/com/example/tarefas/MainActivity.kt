package com.example.tarefas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tarefas.ui.theme.TarefasTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TarefasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color  = MaterialTheme.colorScheme.background
                ) {
                    TarefasImage(
                        texto1 = stringResource(R.string.texto1),
                        texto2 = stringResource(R.string.texto2)
                    )
                }
            }
        }
    }
}


@Composable
fun TarefasText(texto1: String, texto2: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = texto1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 24.dp, bottom = 8.dp)
        )
        Text(
            text = texto2,
            fontSize = 16.sp
        )
    }
}

@Composable
fun TarefasImage(texto1: String, texto2: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.ic_task_completed)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image,
            contentDescription = null
        )
        TarefasText(
            texto1 = texto1,
            texto2 = texto2
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TarefasPreview() {
    TarefasTheme {
        TarefasImage(
            texto1 = stringResource(R.string.texto1),
            texto2 = stringResource(R.string.texto2),
        )
    }
}
