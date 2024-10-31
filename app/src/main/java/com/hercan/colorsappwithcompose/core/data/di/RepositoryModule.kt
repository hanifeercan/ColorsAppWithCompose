package com.hercan.colorsappwithcompose.core.data.di

import com.hercan.colorsappwithcompose.core.data.repo.ColorRepository
import com.hercan.colorsappwithcompose.core.data.repo.ColorRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindColorRepository(bookRepositoryImpl: ColorRepositoryImpl): ColorRepository
}