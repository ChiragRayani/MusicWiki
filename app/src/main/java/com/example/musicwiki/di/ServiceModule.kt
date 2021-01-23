package com.example.musicwiki.di

import com.example.musicwiki.service.MusicService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideMusicService(retrofit: Retrofit): MusicService {
        return retrofit.create(MusicService::class.java)
    }
}