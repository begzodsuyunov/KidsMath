package com.example.kidsmath.data.repository

import com.example.kidsmath.data.model.Question
import com.example.kidsmath.data.room.entity.GameEntity
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getAllEasyLevel(): Flow<List<GameEntity>>

    fun getAllMediumLevel(): Flow<List<GameEntity>>

    fun getAllHardLevel(): Flow<List<GameEntity>>

    fun update(entity: GameEntity)

    suspend fun generateQuestion()

    fun getLevel(): Flow<String>

    suspend fun openNextLevel(level: Int, number: Int)

    suspend fun setLevel(level: String)

    fun getByNumber(level: Int, number: Int): Flow<List<Question>>
}