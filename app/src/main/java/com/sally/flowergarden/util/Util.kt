package com.sally.flowergarden.util

import android.content.Context
import androidx.room.Room
import com.sally.flowergarden.model.FlowerDatabase

val DB_NAME = "flowerdb"

fun buildDb(context: Context): FlowerDatabase {
    val db = Room.databaseBuilder(context, FlowerDatabase::class.java, DB_NAME).build()
    return db
}