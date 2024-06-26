package org.openapp.splitmoney.models

data class Transaction(var description: String = "Hello", var amount: Double = 0.0, var members: List<Int>, var payer: Number)