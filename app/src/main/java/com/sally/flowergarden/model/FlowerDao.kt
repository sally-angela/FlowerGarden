package com.sally.flowergarden.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FlowerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg flower: Flower)

    @Query("SELECT * FROM flower")
    fun selectAllFlower(): List<Flower>

    @Query("SELECT * FROM flower WHERE id= :id")
    fun selectFlower(id:Int): Flower

    @Query("UPDATE flower SET title=:title, author=:author, description=:description, images=:images WHERE id = :id")
    fun updateFlower(title:String, author:String, description:String, images:String, id:Int)

    @Delete
    fun deleteFlower(flower: Flower)
}