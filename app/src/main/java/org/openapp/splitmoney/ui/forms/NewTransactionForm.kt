package org.openapp.splitmoney.ui.forms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.openapp.splitmoney.models.Transaction

@Composable
fun NewTransactionForm(transaction: Transaction) {

    var description by rememberSaveable { mutableStateOf(transaction.description) }
    var amount by rememberSaveable { mutableStateOf(transaction.amount.toString()) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {
        TextField(
            leadingIcon = {
                Icon(
                    Icons.Outlined.Menu,
                    contentDescription = "Localized description",
                )
            },
            value = description,
            onValueChange = {
                description = it
                transaction.description = it
            },
            placeholder = { Text("Enter a description") }
        )

        TextField(
            leadingIcon = {
                Icon(
                    Icons.Filled.ShoppingCart,
                    contentDescription = "Localized description",
                )
            },
            value = amount,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                try {
                    amount = it
                    transaction.amount = it.toDouble()
                }catch (_: NumberFormatException) {
                    transaction.amount = 0.0
                }
            },
            placeholder = { Text("Enter amount") }
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Paid by")

            Button(onClick = { /*TODO*/ }) {
                Text(transaction.payer.toString())
            }

            Text("and split")

            Button(onClick = { /*TODO*/ }) {
                Text("equally")
            }
        }
    }
}