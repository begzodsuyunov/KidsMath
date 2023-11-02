package com.example.kidsmath.presentation.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kidsmath.presentation.navigator.Navigator
import com.example.kidsmath.presentation.screen.fragment.SplashFragmentDirections
import com.example.kidsmath.presentation.viewmodel.SplashViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val navigator: Navigator
): SplashViewModel, ViewModel() {

    override fun openMenuScreen() {
        viewModelScope.launch {
            delay(2500)
            navigator.navigateTo(SplashFragmentDirections.actionSplashFragmentToMenuFragment())
        }
    }


}