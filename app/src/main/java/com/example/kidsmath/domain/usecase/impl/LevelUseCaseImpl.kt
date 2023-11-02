package com.example.kidsmath.domain.usecase.impl

import com.example.kidsmath.data.repository.Repository
import com.example.kidsmath.data.room.entity.GameEntity
import com.example.kidsmath.domain.usecase.LevelUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LevelUseCaseImpl @Inject constructor(
    private val repository: Repository
): LevelUseCase {
    override fun getAllEasyLevel(): Flow<List<GameEntity>> = repository.getAllEasyLevel()

    override fun getAllMediumLevel(): Flow<List<GameEntity>> = repository.getAllMediumLevel()

    override fun getAllHardLevel(): Flow<List<GameEntity>> = repository.getAllHardLevel()

}