package com.example.musicwiki.di

import com.example.musicwiki.repository.GenreDetailRepository
import com.example.musicwiki.repository.MainRepository
import com.example.musicwiki.service.MusicService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideMainRepository(
        service: MusicService
    ): MainRepository {
        return MainRepository(service)
    }

    @Provides
    @ActivityRetainedScoped
    fun provideGenreRepository(
        service: MusicService
    ): GenreDetailRepository {
        return GenreDetailRepository(service)
    }

}