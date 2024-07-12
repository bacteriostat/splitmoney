package org.openapp.splitmoney

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.Done
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.openapp.splitmoney.models.Member
import org.openapp.splitmoney.models.Transaction
import org.openapp.splitmoney.ui.pages.MembersPage
import org.openapp.splitmoney.ui.pages.NewTransactionForm
import org.openapp.splitmoney.ui.pages.TransactionsPage
import org.openapp.splitmoney.ui.theme.SplitmoneyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Navigator()
        }
    }
}

@Composable
fun Navigator(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") { Home() }
        // Add more destinations similarly.
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {
    val showNewTransactionForm = remember { mutableStateOf(false) }
    val currentPage = remember { mutableStateOf("Transactions") }

    SplitmoneyTheme {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.List, contentDescription = "Transactions") },
                        label = { Text("Transactions") },
                        selected = currentPage.value == "Transactions",
                        onClick = {
                            currentPage.value = "Transactions"
                        },
                    )

                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Person, contentDescription = "Members") },
                        label = { Text("Members") },
                        selected = currentPage.value == "Members",
                        onClick = {
                            currentPage.value = "Members"
                        },
                    )
                }
            },
            topBar = {
                MediumTopAppBar(
                    title = {
                        Text(currentPage.value)
                    },
                    scrollBehavior = scrollBehavior,
                    actions = {
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "More Options"
                            )
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        showNewTransactionForm.value = true
                    }
                ) {
                    Icon(Icons.Filled.Add, "Floating action button.")
                }
            }
        ) { innerPadding ->

            if(currentPage.value == "Transactions")
                TransactionsPage(innerPadding = innerPadding)
            else if(currentPage.value == "Members")
                MembersPage(members = listOf(Member(1, "Shavez")), innerPadding)

            NewTransactionFormDialog(showNewTransactionForm = showNewTransactionForm) {
                showNewTransactionForm.value = false
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTransactionFormDialog(showNewTransactionForm: MutableState<Boolean>, onDismissRequest: () -> Unit) {
    val newTransaction by remember { mutableStateOf(
        Transaction(
            members = listOf(
                Member(id = 1, name="Shavez"),
                Member(id = 2, name="Abhinav"),
                Member(id = 3, name="Abhishek")
            ),
            payer = Member(id = 1, name="Shavez")
        )
    ) }
    if(showNewTransactionForm.value) {
        Dialog(
            onDismissRequest = { onDismissRequest() },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("New Transaction")
                            },
                            navigationIcon = {
                                IconButton(onClick = { onDismissRequest() }) {
                                    Icon(
                                        imageVector = Icons.Sharp.Close,
                                        contentDescription = "Close New Transaction Form"
                                    )
                                }
                            },
                            actions = {
                                IconButton(onClick = {
                                    println(newTransaction.amount)
                                    onDismissRequest()
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
        }
    }
}