package com.example.kidsmath.domain.usecase

import com.example.kidsmath.data.room.entity.GameEntity
import kotlinx.coroutines.flow.Flow

interface LevelUseCase {
    fun getAllEasyLevel(): Flow<List<GameEntity>>

    fun getAllMediumLevel(): Flow<List<GameEntity>>

    fun getAllHardLevel(): Flow<List<GameEntity>>
}