package org.openapp.splitmoney.ui.pages

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavController
import org.openapp.splitmoney.models.Member
import org.openapp.splitmoney.ui.common.MembersList
import org.openapp.splitmoney.ui.common.TransactionsList
import org.openapp.splitmoney.ui.theme.SplitmoneyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) {
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
                        navController.navigate("NewTransaction")
                    }
                ) {
                    Icon(Icons.Filled.Add, "Floating action button.")
                }
            }
        ) { innerPadding ->

            if(currentPage.value == "Transactions")
                TransactionsList(innerPadding = innerPadding)
            else if(currentPage.value == "Members")
                MembersList(members = listOf(Member(1, "Shavez")), innerPadding)
        }
    }
}