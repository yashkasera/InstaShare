package com.yashkasera.instashare.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.yashkasera.instashare.data.InstaShareDatabase
import com.yashkasera.instashare.data.Repository
import com.yashkasera.instashare.model.*

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val context: Context by lazy { application.applicationContext }
    val repository =
        Repository(context, InstaShareDatabase.getInstance(context))

    fun getRecords(domain: String): LiveData<Result<List<Record>>> {
        return repository.getRecords()
    }
}