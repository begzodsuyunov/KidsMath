package com.example.kidsmath.domain.usecase

import com.example.kidsmath.data.model.Question
import com.example.kidsmath.data.room.entity.GameEntity
import kotlinx.coroutines.flow.Flow

interface GameUseCase {

    fun getByNumber(level: Int, number: Int): Flow<List<Question>>

    suspend fun saveResult(entity: GameEntity)

    suspend fun openNextLevel(level: Int, number: Int)


}