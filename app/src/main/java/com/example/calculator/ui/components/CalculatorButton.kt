package com.example.calculator.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.theme.Grey1
import com.example.calculator.ui.theme.Grey2
import com.example.calculator.ui.theme.Orange


@Composable
fun CalculatorButton(
    label: String,
    modifier: Modifier = Modifier,
    onButtonPress: (String) -> Unit
) {
    val backgroundColor = when (label) {
        "AC", "+/-", "%" -> Grey1
        "÷", "x", "-", "+", "=" -> Orange
        else -> Grey2
    }
    Button(
        onClick = { onButtonPress(label) },
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