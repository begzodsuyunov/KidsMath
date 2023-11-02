package com.example.kidsmath.domain.di

import com.example.kidsmath.presentation.navigator.NavigationHandler
import com.example.kidsmath.presentation.navigator.Navigator
import com.example.kidsmath.presentation.navigator.NavigatorDispatcher
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun navigator(dispatcher: NavigatorDispatcher): Navigator

    @Binds
    fun handler(dispatcher: NavigatorDispatcher): NavigationHandler
}