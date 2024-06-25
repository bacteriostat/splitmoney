package org.openapp.splitmoney

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.Done
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.openapp.splitmoney.ui.forms.NewTransactionForm
import org.openapp.splitmoney.ui.theme.SplitmoneyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StartView()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartView(){
    val openNewTransactionForm = remember { mutableStateOf(false) }
    SplitmoneyTheme {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                MediumTopAppBar(
                    title = {
                        Text("Splitmoney")
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
                        openNewTransactionForm.value = true
                    }
                ) {
                    Icon(Icons.Filled.Add, "Floating action button.")
                }
            }
        ) { innerPadding ->
            val list = listOf("Momos", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger", "Burger");
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                items(list) { item -> TransactionItemView(item, 5.0) }
            }

            NewTransactionFormDialog(showNewTransactionForm = openNewTransactionForm) {
                openNewTransactionForm.value = false
            }
        }
    }
}

@Composable
fun TransactionItemView(name: String, total: Double, modifier: Modifier = Modifier) {
    ListItem(
        headlineContent = {
            Text(text = name)
        },
        leadingContent = {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Localized description",
            )
        },
        supportingContent = {
            Text(text = "$ $total")
        }

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTransactionFormDialog(showNewTransactionForm: MutableState<Boolean>, onDismissRequest: () -> Unit) {
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
                                IconButton(onClick = { showNewTransactionForm.value = false }) {
                                    Icon(
                                        imageVector = Icons.Sharp.Close,
                                        contentDescription = "Close New Transaction Form"
                                    )
                                }
                            },
                            actions = {
                                IconButton(onClick = { showNewTransactionForm.value = false }) {
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
                        NewTransactionForm()
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    SplitmoneyTheme {
//        Greeting("Android")
//    }
//}