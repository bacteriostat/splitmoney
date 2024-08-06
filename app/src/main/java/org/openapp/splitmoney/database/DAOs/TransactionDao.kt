package org.openapp.splitmoney.database.DAOs

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.openapp.splitmoney.database.entities.Member
import org.openapp.splitmoney.database.entities.Transaction

@Dao
interface TransactionDao {
    @Query("SELECT * FROM 'transaction'")
    suspend fun getAll(): List<Transaction>

    @Insert
    suspend fun insertAll(vararg transactions: Transaction)

}