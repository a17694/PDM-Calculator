package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.formatWithSkeleton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.theme.CalculatorTheme

@Composable
fun CalculatorView(modifier: Modifier = Modifier) {
    var display by remember { mutableStateOf("0") }

    val buttons = listOf(
        "AC", "+/-", "%", "÷",
        "7", "8", "9", "×",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "ICONE", "0", ",", "="
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        horizontalAlignment = Alignment.End
    ) {
        // Display
        Text(
            text = display,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.End),
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )

        // Button grid layout
        buttons.chunked(4).forEach { rowButtons ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                rowButtons.forEach { label ->
                    CalculatorButton(
                        label = label,
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
        }
    }

}


@Composable
fun CalculatorButton(label: String, modifier: Modifier = Modifier) {
    val backgroundColor = when (label) {
        "AC", "+/-", "%" -> Color(0xFF838384)
        "÷", "×", "-", "+", "=" -> Color(0xFFFFA726)
        else -> Color(0xFF626262)
    }
    Button(
        onClick = { /* Adicione a lógica do clique aqui */ },
        modifier = modifier
            .aspectRatio(1f)
            .padding(4.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor),
        contentPadding = PaddingValues(4.dp)
    ) {
        Text(
            text = label,
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    CalculatorTheme {
        CalculatorView()
    }
}