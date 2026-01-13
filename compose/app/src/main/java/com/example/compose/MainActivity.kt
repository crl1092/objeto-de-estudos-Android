package com.example.compose

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion.Justify
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    ComposeImageCard(
                        titulo = stringResource(R.string.jetpack_compose_tutorial),
                        p1 = stringResource(R.string.p1),
                        p2 = stringResource(R.string.p2)
                    )
                }
            }
        }
    }
}

@Composable
fun ComposeText(Titulo: String,p1: String,p2: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = Titulo,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
        )

        Text(
            text = p1,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 16.sp,
            textAlign = Justify,
            modifier = Modifier
                .padding(16.dp, 0.dp, 16.dp, 0.dp)
        )
        Text(
            text = p2,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 16.sp,
            textAlign = Justify,
            modifier = Modifier
                .padding(16.dp)
        )
    }

}

@Composable
fun ComposeImageCard (titulo: String, p1: String, p2: String, modifier: Modifier = Modifier){
    val image = painterResource(R.drawable.bg_compose_background)
    Column(modifier = modifier) {
        Image(
            painter = image,
            contentDescription = null
        )
        ComposeText(
            Titulo = titulo,
            p1 = p1,
            p2 = p2
        )

    }
}

@Preview(showBackground = true)
@Composable
fun ComposePreview() {
    ComposeTheme {
        ComposeImageCard(
            titulo = stringResource(R.string.jetpack_compose_tutorial),
            p1 = stringResource(R.string.p1),
            p2 = stringResource(R.string.p2)
        )
    }
}
