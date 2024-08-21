package org.openapp.splitmoney.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo var description: String,
    @ColumnInfo var amount: Double,
    @ColumnInfo var members: Int,
    @ColumnInfo var payer: Int
)