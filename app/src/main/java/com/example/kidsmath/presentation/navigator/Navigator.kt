package com.example.kidsmath.presentation.navigator

import androidx.navigation.NavDirections


typealias Direction = NavDirections


interface Navigator {
    suspend fun navigateTo(direction: Direction)
    suspend fun back()
}