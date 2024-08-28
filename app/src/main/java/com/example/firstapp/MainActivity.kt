package com.example.firstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HandiApp()
        }
    }

}

@Composable
fun HandiApp() {
    var connected by remember { mutableStateOf(false) }
    var sliderValues = remember { mutableStateListOf(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f) }
    val maxAngleValues = remember { mutableStateListOf(380.0f, 380.0f, 400.0f, 400.0f, 400.0f, 200.0f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "HANDI EPN",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (connected) "Conectado" else "Desconectado",
            color = if (connected) Color.Green else Color.Red,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Button(
            onClick = { connected = !connected },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Bluetooth")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Introducción Control Multivariable",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        for (i in 1..6) {
            ControlRow(
                label = "Dedo $i",
                sliderValue = sliderValues[i - 1],
                onValueChange = { sliderValues[i - 1] = it },
                maxAngleValue = maxAngleValues[i - 1],
                angleLabel = "Ángulo $i: "
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { /* TODO: Actualizar */ }) {
                Text(text = "Actualizar")
            }
            Button(onClick = { /* TODO: Cerrar */ }) {
                Text(text = "Cerrar")
            }
            Button(onClick = { /* TODO: Calibrar */ }) {
                Text(text = "Calibrar")
            }
        }
    }
}

@Composable
fun ControlRow(
    label: String,
    sliderValue: Float,
    onValueChange: (Float) -> Unit,
    maxAngleValue: Float,
    angleLabel: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = label, modifier = Modifier.weight(1f))
        Slider(
            value = sliderValue,
            onValueChange = onValueChange,
            valueRange = 0f..maxAngleValue,
            modifier = Modifier.weight(2f)
        )
        Text(text = angleLabel)
        Text(text = sliderValue.toInt().toString(), modifier = Modifier.width(32.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHandiApp() {
    HandiApp()
}