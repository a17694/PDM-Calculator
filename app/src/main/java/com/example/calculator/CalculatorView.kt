package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.components.CalculatorButton


@Composable
fun CalculatorView(modifier: Modifier = Modifier) {

    var display by remember { mutableStateOf("0") }

    var calculatorBrain = remember { CalculatorBrain() }

    fun convertToDecimal(input: String): String {
        return input.replace(',', '.')
    }

    fun unConvertToDecimal(input: String): String {
        return input.replace('.', ',')
    }

    fun getDisplay(): Double {
        display = convertToDecimal(display)
        return display.toDouble()
    }

    fun setDisplay(value: Double) {
        if (value % 1 == 0.0) {
            display = unConvertToDecimal(value.toInt().toString())
        } else {
            display = unConvertToDecimal(value.toString())
        }
    }

    var userIsInTheMiddleOfTyping by remember {
        mutableStateOf(false)
    }

    val buttons = listOf(
        "AC", "+/-", "%", "÷",
        "7", "8", "9", "x",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "", "0", ",", "="
    )

    val onNumPress: (String) -> Unit = { num ->
        if (userIsInTheMiddleOfTyping) {
            if (display == "0") {
                if (num == ".") {
                    display = "0."
                } else {
                    display = num
                }
            } else {
                if (num == ".") {
                    if (!display.contains(".")) {
                        display += num
                    }
                } else {
                    display += num
                }
            }
        } else {
            display = num
        }

        userIsInTheMiddleOfTyping = true
    }

    val onOperationPressed: (String) -> Unit = { op ->

        if (CalculatorBrain.Operation.RESET.op == op) {
            display = "0"
            calculatorBrain.operand = 0.0
        } else {

            //calculatorBrain.operand

            userIsInTheMiddleOfTyping = false
            setDisplay(calculatorBrain.doOperation(getDisplay()))
            calculatorBrain.operand = getDisplay()
            calculatorBrain.operation = CalculatorBrain.Operation.getOp(op)
        }
    }

    val onButPress: (String) -> Unit = { label ->
        val operation = CalculatorBrain.Operation.getOp(label)
        when (operation) {
            CalculatorBrain.Operation.SUM,
            CalculatorBrain.Operation.SUB,
            CalculatorBrain.Operation.MULT,
            CalculatorBrain.Operation.DIV,
            CalculatorBrain.Operation.PERCENT,
            CalculatorBrain.Operation.SQRT,
            CalculatorBrain.Operation.RESET,
            CalculatorBrain.Operation.EQUAL,
            CalculatorBrain.Operation.SIGNAL -> {
                onOperationPressed(label)
            }
            else -> {
                onNumPress(label)
            }
        }
    }

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
                .padding(30.dp)
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
                        onButtonPress = onButPress,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

