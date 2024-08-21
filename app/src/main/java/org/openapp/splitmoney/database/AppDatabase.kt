package org.openapp.splitmoney.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.openapp.splitmoney.database.DAOs.MemberDao
import org.openapp.splitmoney.database.DAOs.TransactionDao
import org.openapp.splitmoney.database.entities.Member
import org.openapp.splitmoney.database.entities.Transaction

@Database(entities = [Member::class, Transaction::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memberDao(): MemberDao
    abstract fun transactionDao(): TransactionDao
}