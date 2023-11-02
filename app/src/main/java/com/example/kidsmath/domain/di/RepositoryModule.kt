package com.example.kidsmath.domain.di

import com.example.kidsmath.data.repository.Repository
import com.example.kidsmath.data.repository.impl.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindRepository(impl: RepositoryImpl) : Repository

}