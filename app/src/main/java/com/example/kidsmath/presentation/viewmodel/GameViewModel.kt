package com.example.kidsmath.presentation.viewmodel

import com.example.kidsmath.data.model.Question
import com.example.kidsmath.data.room.entity.GameEntity
import kotlinx.coroutines.flow.Flow

interface GameViewModel {

    val gameModelLiveData: Flow<List<Question>>

    fun back()

    fun getByNumber(level: Int, number: Int)

    fun saveResult(entity: GameEntity)

    fun btnClicked(state: Boolean, position: Int)

    fun openNextLevel(level: Int, number: Int)
}