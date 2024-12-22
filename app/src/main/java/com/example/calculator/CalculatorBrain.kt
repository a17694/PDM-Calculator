package com.example.calculator

class CalculatorBrain {
    enum class Operation(val op: String) {
        SUM("+"),
        SUB("-"),
        MULT("x"),
        DIV("÷"),
        SQRT("√"),
        PERCENT("%"),
        EQUAL("="),
        RAND("\uD83D\uDE02");

        companion object {
            fun getOp(value: String): Operation {
                return entries.find { it.op == value } ?: RAND
            }
        }
    }

    var operation: Operation? = null
    var operand: Double = 0.0

    fun doOperation(value: Double): Double {
        val result = when (operation) {
            Operation.SUM -> operand + value
            Operation.SUB -> operand - value
            Operation.MULT -> operand * value
            Operation.DIV -> if (value != 0.0) operand / value else Double.NaN
            Operation.SQRT -> Math.sqrt(operand)
            Operation.PERCENT -> operand / 100
            Operation.RAND -> Math.random()
            Operation.EQUAL -> operand
            null -> value
        }
        return result
    }
}