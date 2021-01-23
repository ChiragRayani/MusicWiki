package com.example.musicwiki.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object MainModule {

   /* @Provides
    @ActivityRetainedScoped
    fun provideMainRespository(
        service:Service
    ): MainRepository {
        return MainRepository(service)
    }*/

}