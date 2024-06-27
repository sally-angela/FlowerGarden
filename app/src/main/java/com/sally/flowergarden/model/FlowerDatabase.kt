package com.sally.flowergarden.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sally.flowergarden.util.DB_NAME
import com.sally.flowergarden.util.MIGRATION_1_2

@Database(entities = [Flower::class, User::class], version = 2)

abstract class FlowerDatabase:RoomDatabase() {
    abstract fun flowerDao(): FlowerDao
    abstract fun userDao(): UserDao

    companion object{
        @Volatile private var instance: FlowerDatabase ?= null
        private val LOCK = Any()

        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            FlowerDatabase::class.java, DB_NAME)
            .addMigrations(MIGRATION_1_2)
            .build()

        operator fun invoke(context: Context) {
            if(instance != null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}