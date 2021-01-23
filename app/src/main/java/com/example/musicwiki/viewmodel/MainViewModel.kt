package com.example.musicwiki.viewmodel

import android.app.Application
import androidx.core.content.ContextCompat
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.example.musicwiki.R
import com.example.musicwiki.repository.MainRepository
import com.example.musicwiki.util.ResponseWrapper
import kotlinx.coroutines.Dispatchers

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository,
    application: Application
) : AndroidViewModel(application) {

    var context = application

    fun getTags() = liveData(Dispatchers.IO) {
        emit(ResponseWrapper.loading())
        val data = repository.getGenre(context.getString(R.string.api_key))
        if (data.isSuccessful) {
            emit(ResponseWrapper.success(data.body()))
        } else {
            emit(ResponseWrapper.error(null, data.code(), "Something went wrong!"))
        }
    }
}