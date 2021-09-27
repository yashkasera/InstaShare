package com.yashkasera.instashare.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yashkasera.instashare.util.RecordType

@Entity
data class Record(
    @PrimaryKey(autoGenerate = true)
    val _id: Long,
    val type: RecordType,
    val value: String,
    val date: String
)
