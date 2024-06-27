package com.sally.flowergarden.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sally.flowergarden.model.FlowerDatabase

val DB_NAME = "flowerdb"

fun buildDb(context: Context): FlowerDatabase {
    val db = Room.databaseBuilder(context, FlowerDatabase::class.java, DB_NAME).build()
    return db
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE flower ADD COLUMN images TEXT DEFAULT 'https://loremflickr.com/320/240/flower?lock=1' not null")
    }
}