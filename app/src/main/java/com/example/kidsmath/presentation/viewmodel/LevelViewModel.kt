package com.example.kidsmath.presentation.viewmodel

import com.example.kidsmath.data.room.entity.GameEntity
import kotlinx.coroutines.flow.Flow

interface LevelViewModel {

    val easyLevelsList: Flow<List<GameEntity>>
    val mediumLevelsList: Flow<List<GameEntity>>
    val hardLevelsList: Flow<List<GameEntity>>

    fun back()

    fun openGameScreen(gameEntity: GameEntity)

    fun generateEasy()

    fun generateMedium()

    fun generateHard()

}