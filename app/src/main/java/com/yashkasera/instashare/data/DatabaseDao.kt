package com.yashkasera.instashare.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yashkasera.instashare.model.Record

@Dao
interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: Record)

    @Delete
    suspend fun deleteRecord(record: Record)

    @Query("SELECT * FROM RECORD")
    fun getRecords(): LiveData<List<Record>>
}