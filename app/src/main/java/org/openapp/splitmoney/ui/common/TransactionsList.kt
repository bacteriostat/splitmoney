package org.openapp.splitmoney.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.openapp.splitmoney.database.entities.Transaction

@Composable
fun TransactionsList(transactions: List<Transaction>, innerPadding: PaddingValues) {

    LazyColumn(modifier = Modifier.padding(innerPadding)) {
        items(transactions) { item -> TransactionItemView(item) }
    }
}

@Composable
fun TransactionItemView(transaction: Transaction) {
    ListItem(
        headlineContent = {
            Text(text = transaction.description)
        },
        leadingContent = {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Localized description",
            )
        },
        supportingContent = {
            Text(text = "${transaction.payer} paid $${transaction.amount}")
        }

    )
}