package org.openapp.splitmoney

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import org.openapp.splitmoney.database.AppDatabase
import org.openapp.splitmoney.ui.pages.Home
import org.openapp.splitmoney.ui.pages.NewTransactionFormPage

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
        composable("Home") { Home(navController) }
        composable("NewTransaction") { NewTransactionFormPage(navController) }
        // Add more destinations similarly.
    }
}
