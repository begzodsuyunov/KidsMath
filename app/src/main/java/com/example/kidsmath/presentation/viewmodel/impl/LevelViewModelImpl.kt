package com.example.kidsmath.presentation.viewmodel.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kidsmath.data.room.entity.GameEntity
import com.example.kidsmath.domain.usecase.LevelUseCase
import com.example.kidsmath.presentation.navigator.Navigator
import com.example.kidsmath.presentation.screen.fragment.LevelFragmentDirections
import com.example.kidsmath.presentation.viewmodel.LevelViewModel
import com.example.kidsmath.utils.eventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LevelViewModelImpl @Inject constructor(
    private val useCase: LevelUseCase,
    private val navigator: Navigator
): LevelViewModel, ViewModel() {


    override val easyLevelsList = eventFlow<List<GameEntity>>()
    override val mediumLevelsList = eventFlow<List<GameEntity>>()
    override val hardLevelsList = eventFlow<List<GameEntity>>()

    override fun back() {
        viewModelScope.launch {
            navigator.back()
        }
    }

    override fun openGameScreen(gameEntity: GameEntity) {
        viewModelScope.launch {
            navigator.navigateTo(LevelFragmentDirections.actionLevelFragmentToGameFragment(gameEntity))
        }
    }

    override fun generateEasy() {
        viewModelScope.launch {
            useCase.getAllEasyLevel().collectLatest {
                easyLevelsList.emit(it)
            }
        }
    }

    override fun generateMedium() {
        viewModelScope.launch {
            useCase.getAllMediumLevel().collectLatest {
                mediumLevelsList.emit(it)
            }
        }
    }

    override fun generateHard() {
        viewModelScope.launch {
            useCase.getAllHardLevel().collectLatest {
                hardLevelsList.emit(it)
            }
        }
    }
}