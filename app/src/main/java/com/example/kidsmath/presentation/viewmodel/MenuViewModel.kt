package com.example.kidsmath.presentation.viewmodel

import kotlinx.coroutines.flow.Flow

interface MenuViewModel {

    val levelFlow: Flow<String>

    val showLevelButton: Flow<Boolean>
    val showSettingButton: Flow<Boolean>
    val showExitButton: Flow<Boolean>
    val showInfoButton: Flow<Boolean>

    suspend fun setLevel(level: String)

    fun onClickEasy(level: String)

    fun onClickMedium(level: String)

    fun onClickHard(level: String)

    fun onCLickPlayNowButton(level: String)

    fun onClickLevelButton()

    fun onClickSettingButton()

    fun onClickExitButton()

    fun generate()

    fun onClickInfoButton()

}