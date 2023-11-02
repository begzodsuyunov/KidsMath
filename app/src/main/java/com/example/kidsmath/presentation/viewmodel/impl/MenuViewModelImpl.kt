package com.example.kidsmath.presentation.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kidsmath.domain.usecase.MenuUseCase
import com.example.kidsmath.presentation.navigator.Navigator
import com.example.kidsmath.presentation.screen.fragment.MenuFragmentDirections
import com.example.kidsmath.presentation.viewmodel.MenuViewModel
import com.example.kidsmath.utils.eventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModelImpl @Inject constructor(
    private val navigator: Navigator,
    private val useCase: MenuUseCase
) : MenuViewModel, ViewModel() {


    override val levelFlow = eventFlow<String>()
    override val showLevelButton = eventFlow<Boolean>()
    override val showSettingButton = eventFlow<Boolean>()
    override val showExitButton = eventFlow<Boolean>()
    override val showInfoButton = eventFlow<Boolean>()

    init {
        viewModelScope.launch {
            useCase.getLevel().collectLatest {
                levelFlow.emit(it)
            }
        }
    }

    override suspend fun setLevel(level: String)  = useCase.setLevel(level)


    override fun onClickEasy(level: String) {
        viewModelScope.launch {
            levelFlow.emit("easy")
        }
    }

    override fun onClickMedium(level: String) {
        viewModelScope.launch {
            levelFlow.emit("medium")
        }
    }

    override fun onClickHard(level: String) {
        viewModelScope.launch {
            levelFlow.emit("hard")
        }
    }

    override fun onCLickPlayNowButton(level: String) {
        viewModelScope.launch{
            navigator.navigateTo(MenuFragmentDirections.actionMenuFragmentToLevelFragment(level))
        }
    }

    override fun onClickLevelButton() {
        viewModelScope.launch {
            showLevelButton.emit(true)
        }
    }

    override fun onClickSettingButton() {
        viewModelScope.launch {
            showSettingButton.emit(true)
        }
    }

    override fun onClickExitButton() {
        viewModelScope.launch {
            showExitButton.emit(true)
        }
    }

    override fun generate() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.generate()
        }
    }

    override fun onClickInfoButton() {
        viewModelScope.launch {
            showInfoButton.emit(true)
        }
    }
}