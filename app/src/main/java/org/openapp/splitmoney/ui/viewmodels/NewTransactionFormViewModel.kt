package org.openapp.splitmoney.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.openapp.splitmoney.database.AppDatabase
import org.openapp.splitmoney.database.entities.Member
import org.openapp.splitmoney.database.entities.Transaction

data class NewTransactionFormUiState(
    val transactions: List<Transaction> = listOf(),
    val members: List<Member> = listOf()
)

class NewTransactionFormViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(NewTransactionFormUiState())
    val uiState: StateFlow<NewTransactionFormUiState> = _uiState.asStateFlow()
    private lateinit var _db: AppDatabase;


    fun initDB(applicationContext: Context) {
        viewModelScope.launch {
            _db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "splitmoney-db"
            ).fallbackToDestructiveMigration().build()
        }
    }

    fun getMembers() {
        viewModelScope.launch {

            val members: List<Member> = _db.memberDao().getAll()

            _uiState.value = NewTransactionFormUiState(
                _uiState.value.transactions,
                members
            )
        }
    }

    fun getTransactions() {
        viewModelScope.launch {

            val transactions: List<Transaction> = _db.transactionDao().getAll()

            _uiState.value = NewTransactionFormUiState(
                transactions,
                _uiState.value.members
            )
        }
    }

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {

            _db.transactionDao().insertAll(transaction)

            getTransactions()

        }
    }


}