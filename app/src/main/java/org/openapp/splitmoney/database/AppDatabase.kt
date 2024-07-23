package org.openapp.splitmoney.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.openapp.splitmoney.database.DAOs.MemberDao
import org.openapp.splitmoney.database.entities.Member

@Database(entities = [Member::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memberDao(): MemberDao
}