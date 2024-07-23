package org.openapp.splitmoney.database.DAOs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import org.openapp.splitmoney.database.entities.Member

@Dao
interface MemberDao {
    @Query("SELECT * FROM member")
    fun getAll(): List<Member>

    @Query("SELECT * FROM member WHERE id IN (:memberIds)")
    fun loadAllByIds(memberIds: IntArray): List<Member>

    @Query("SELECT * FROM member WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): Member

    @Insert
    fun insertAll(vararg users: Member)

    @Delete
    fun delete(user: Member)

}