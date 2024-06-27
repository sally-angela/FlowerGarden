package com.sally.flowergarden.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg user: User)

    @Query("SELECT * FROM user WHERE id=:id")
    fun selectUser(id:Int): User

    @Query("SELECT * FROM user WHERE username=:username AND password=:password")
    fun login(username:String, password: String): User

    @Query("UPDATE user SET firstName=:firstName, lastName=:lastName, password=:password WHERE id = :id")
    fun updateUser(firstName:String, lastName:String, password:String, id:Int)

    @Delete
    fun deleteUser(user: User)
}