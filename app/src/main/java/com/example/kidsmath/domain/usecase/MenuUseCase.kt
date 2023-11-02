package com.example.kidsmath.domain.usecase

import kotlinx.coroutines.flow.Flow

interface MenuUseCase {
    fun getLevel(): Flow<String>

    suspend fun setLevel(level: String)

    suspend fun generate()

}