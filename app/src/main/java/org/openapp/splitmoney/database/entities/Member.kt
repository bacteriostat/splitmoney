package org.openapp.splitmoney.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Member(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String
)
