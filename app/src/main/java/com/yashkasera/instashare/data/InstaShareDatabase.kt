package com.yashkasera.instashare.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yashkasera.instashare.model.Record

private const val TAG = "InstaShareDatabase"

@Database(entities = [Record::class], version = 1, exportSchema = false)
abstract class InstaShareDatabase : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: InstaShareDatabase? = null

        fun getInstance(context: Context): InstaShareDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                InstaShareDatabase::class.java, "InstaShareDatabase.db"
            )
                .build()
    }
}