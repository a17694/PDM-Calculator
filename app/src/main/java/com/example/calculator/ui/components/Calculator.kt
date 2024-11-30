package com.example.calculator.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculator.CalculatorView
import com.example.calculator.ui.theme.CalculatorTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalculatorPreview() {
    CalculatorTheme {
        CalculatorView()
    }
}