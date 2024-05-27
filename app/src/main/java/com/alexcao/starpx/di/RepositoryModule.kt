package com.alexcao.starpx.di

import com.alexcao.starpx.repository.Repository
import com.amazonaws.mobile.client.AWSMobileClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(): Repository {
        return Repository(
            awsMobileClient = AWSMobileClient.getInstance()
        )
    }
}