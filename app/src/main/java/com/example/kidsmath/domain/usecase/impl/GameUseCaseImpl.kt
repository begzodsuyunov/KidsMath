package com.example.kidsmath.domain.usecase.impl

import com.example.kidsmath.data.model.Question
import com.example.kidsmath.data.repository.Repository
import com.example.kidsmath.data.room.entity.GameEntity
import com.example.kidsmath.domain.usecase.GameUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameUseCaseImpl @Inject constructor(
    private val repository: Repository
):GameUseCase {

    override fun getByNumber(level: Int, number: Int): Flow<List<Question>> =
        repository.getByNumber(level, number)

    override suspend fun saveResult(entity: GameEntity) =
        repository.update(entity)

    override suspend fun openNextLevel(level: Int, number: Int) =
        repository.openNextLevel(level, number)
}