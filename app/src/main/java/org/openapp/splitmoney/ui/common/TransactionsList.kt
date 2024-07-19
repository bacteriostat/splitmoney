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
import org.openapp.splitmoney.models.Member
import org.openapp.splitmoney.models.Transaction

@Composable
fun TransactionsList(innerPadding: PaddingValues) {
    val list = listOf(
        Transaction(
            description = "Momos",
            amount = 100.0,
            members = listOf(
                Member(id = 1, name="Shavez"),
                Member(id = 2, name="Abhinav"),
                Member(id = 3, name="Abhishek")
            ),
            payer = Member(id = 1, name="Shavez")
        ),
        Transaction(
            description = "Burger",
            amount = 100.0,
            members = listOf(
                Member(id = 1, name="Shavez"),
                Member(id = 2, name="Abhinav"),
                Member(id = 3, name="Abhishek")
            ),
            payer = Member(id = 2, name="Abhinav")
        )
    );
    LazyColumn(modifier = Modifier.padding(innerPadding)) {
        items(list) { item -> TransactionItemView(item) }
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
            Text(text = "${transaction.payer.name} paid $${transaction.amount}")
        }

    )
}