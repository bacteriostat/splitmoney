package org.openapp.splitmoney.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.openapp.splitmoney.database.AppDatabase
import org.openapp.splitmoney.database.entities.Member
import org.openapp.splitmoney.database.entities.Transaction

data class HomeUiState(
    val transactions: List<Transaction> = listOf(),
    val members: List<Member> = listOf()
)

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun getMembers(applicationContext: Context) {
        viewModelScope.launch {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "splitmoney-db"
            ).build()

            val members: List<Member> = db.memberDao().getAll()

            _uiState.value = HomeUiState(
                _uiState.value.transactions,
                members
            )
        }
    }


}