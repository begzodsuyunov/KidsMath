package com.example.kidsmath.presentation.viewmodel.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kidsmath.data.model.Question
import com.example.kidsmath.data.repository.Repository
import com.example.kidsmath.data.room.entity.GameEntity
import com.example.kidsmath.domain.usecase.GameUseCase
import com.example.kidsmath.presentation.navigator.Navigator
import com.example.kidsmath.presentation.viewmodel.GameViewModel
import com.example.kidsmath.utils.eventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModelImpl @Inject constructor(
    private val useCase: GameUseCase,
    private val navigator: Navigator
) : GameViewModel, ViewModel() {


    override val gameModelLiveData = eventFlow<List<Question>>()


    override fun back() {
        viewModelScope.launch {
            navigator.back()
        }
    }

    override fun getByNumber(level: Int, number: Int) {
        viewModelScope.launch {
            useCase.getByNumber(level, number).collect {
                gameModelLiveData.emit(it)
            }
        }
    }

    override fun saveResult(entity: GameEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.saveResult(entity)
        }
    }

    override fun btnClicked(state: Boolean, position: Int) {
        TODO("Not yet implemented")
    }

    override fun openNextLevel(level: Int, number: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.openNextLevel(level, number)
        }
    }
}