package com.example.kidsmath.domain.di

import com.example.kidsmath.domain.usecase.GameUseCase
import com.example.kidsmath.domain.usecase.LevelUseCase
import com.example.kidsmath.domain.usecase.MenuUseCase
import com.example.kidsmath.domain.usecase.impl.GameUseCaseImpl
import com.example.kidsmath.domain.usecase.impl.LevelUseCaseImpl
import com.example.kidsmath.domain.usecase.impl.MenuUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindMenuUseCase(impl: MenuUseCaseImpl): MenuUseCase

    @Binds
    fun bindLevelUseCase(impl: LevelUseCaseImpl): LevelUseCase

    @Binds
    fun bindGameUseCase(impl: GameUseCaseImpl): GameUseCase

}