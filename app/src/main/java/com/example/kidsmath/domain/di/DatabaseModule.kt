package com.example.kidsmath.domain.di

import android.content.Context
import androidx.room.Room
import com.example.kidsmath.data.room.dao.GameDao
import com.example.kidsmath.data.room.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @[Provides Singleton]
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "kidsmath.db").build()
    }

    @[Provides Singleton]
    fun provideDao(database: AppDatabase): GameDao = database.dao()
}