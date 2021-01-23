package com.example.musicwiki.viewmodel

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.example.musicwiki.R
import com.example.musicwiki.repository.GenreDetailRepository
import com.example.musicwiki.util.ResponseWrapper
import kotlinx.coroutines.Dispatchers

class GenreViewModel @ViewModelInject constructor(
    private val repository: GenreDetailRepository,
    application: Application
) : AndroidViewModel(application) {

    val context = application

    fun getTagsInfo(tag: String) = liveData(Dispatchers.IO) {
        emit(ResponseWrapper.loading())
        val data = repository.getTagInfo(context.getString(R.string.api_key), tag)
        if (data.isSuccessful) {
            emit(ResponseWrapper.success(data.body()))
        } else {
            emit(ResponseWrapper.error(null, data.code(), "Something went wrong!"))
        }
    }

    fun getAlbumList(tag: String) = liveData(Dispatchers.IO) {
        emit(ResponseWrapper.loading())
        val data = repository.getAlbumList(context.getString(R.string.api_key), tag)
        if (data.isSuccessful) {
            emit(ResponseWrapper.success(data.body()))
        } else {
            emit(ResponseWrapper.error(null, data.code(), "Something went wrong!"))
        }
    }

    fun getArtistList(tag: String) = liveData(Dispatchers.IO) {
        emit(ResponseWrapper.loading())
        val data = repository.getArtistList(context.getString(R.string.api_key), tag)
        if (data.isSuccessful) {
            emit(ResponseWrapper.success(data.body()))
        } else {
            emit(ResponseWrapper.error(null, data.code(), "Something went wrong!"))
        }
    }

    fun getTrackList(tag: String) = liveData(Dispatchers.IO) {
        emit(ResponseWrapper.loading())
        val data = repository.getTrackList(context.getString(R.string.api_key), tag)
        if (data.isSuccessful) {
            emit(ResponseWrapper.success(data.body()))
        } else {
            emit(ResponseWrapper.error(null, data.code(), "Something went wrong!"))
        }
    }
}