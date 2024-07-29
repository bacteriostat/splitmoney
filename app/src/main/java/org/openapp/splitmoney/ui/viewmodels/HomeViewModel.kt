package org.openapp.splitmoney.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.openapp.splitmoney.database.AppDatabase
import org.openapp.splitmoney.database.entities.Member
import org.openapp.splitmoney.database.entities.Transaction

data class HomeUiState(
    val transactions: List<Transaction> = listOf()
)

class HomeViewModel : ViewModel() {

    private val _transactions = MutableStateFlow(HomeUiState())

    fun getTransactions(applicationContext: Context) {
        viewModelScope.launch {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "splitmoney-db"
            ).build()

//            db.memberDao().insertAll(Member(1, "Shavez"))

            val members: List<Member> = db.memberDao().getAll()

            println(members)
        }
    }


}