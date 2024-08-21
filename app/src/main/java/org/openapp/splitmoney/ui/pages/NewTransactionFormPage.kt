package org.openapp.splitmoney.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.openapp.splitmoney.models.Member
import org.openapp.splitmoney.ui.viewmodels.NewTransactionFormViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.openapp.splitmoney.database.entities.Transaction


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTransactionFormPage(navController: NavController, viewModel: NewTransactionFormViewModel = viewModel()) {

    viewModel.initDB(LocalContext.current);

    val newTransaction by remember {
        mutableStateOf(
            Transaction(
                description = "Hello",
                members = 1,
                payer = 1,
                amount = 20.0
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("New Transaction")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Sharp.Close,
                            contentDescription = "Close New Transaction Form"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        println(newTransaction.amount)
                        viewModel.addTransaction(newTransaction);
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Sharp.Done,
                            contentDescription = "Submit New Transaction"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            NewTransactionForm(newTransaction)
        }
    }
}

@Composable
fun NewTransactionForm(transaction: Transaction) {

    var description by rememberSaveable { mutableStateOf(transaction.description) }
    var amount by rememberSaveable { mutableStateOf(transaction.amount.toString()) }
    var members by remember { mutableStateOf(transaction.members) }
    var payer by remember { mutableStateOf(transaction.payer) }

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

            Button(onClick = {

            }) {
                Text(payer.toString())
            }

            Text("and split")

            Button(onClick = { /*TODO*/ }) {
                Text("equally")
            }
        }
    }
}