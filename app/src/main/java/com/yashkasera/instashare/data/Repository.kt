package com.yashkasera.instashare.data

import android.content.Context
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.yashkasera.instashare.model.Record
import com.yashkasera.instashare.model.Result
import java.io.IOException

private const val TAG = "Repository"

class Repository(
    private val context: Context,
    private val database: InstaShareDatabase
) {
    fun getRecords() = liveData {
        try {
            emitSource(database.databaseDao().getRecords().map {
                if (it.isNotEmpty()) Result.Success(it)
                else Result.Error(NullPointerException("Null"))
            })
        } catch (exception: IOException) {
            emitSource(
                database.databaseDao().getRecords().map {
                    Result.Error(exception)
                }
            )
        }
    }

    suspend fun insertRecord(record: Record){
        try {
            database.databaseDao().insertRecord(record)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun deleteRecord(record: Record) {
        try {
            database.databaseDao().deleteRecord(record)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
}