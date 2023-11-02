package com.example.kidsmath.domain.usecase.impl

import com.example.kidsmath.data.repository.Repository
import com.example.kidsmath.domain.usecase.MenuUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MenuUseCaseImpl @Inject constructor(
    private val repository: Repository
) : MenuUseCase {


    override fun getLevel(): Flow<String> = repository.getLevel()


    override suspend fun setLevel(level: String) =
        repository.setLevel(level)

    override suspend fun generate() {
        repository.generateQuestion()
    }


}