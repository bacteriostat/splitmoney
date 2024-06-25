package org.openapp.splitmoney.ui.forms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Menu
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
import androidx.compose.ui.unit.dp

@Composable
fun NewTransactionForm() {
    var text by rememberSaveable { mutableStateOf("") }
    var amount by rememberSaveable { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
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
            value = text,
            onValueChange = {
                text = it
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
            onValueChange = {
                amount = it
            },
            placeholder = { Text("Enter amount") }
        )
    }
}