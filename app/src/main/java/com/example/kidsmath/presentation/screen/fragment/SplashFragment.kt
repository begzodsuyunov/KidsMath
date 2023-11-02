package com.example.kidsmath.presentation.screen.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kidsmath.R
import com.example.kidsmath.databinding.FragmentSplashBinding
import com.example.kidsmath.presentation.viewmodel.SplashViewModel
import com.example.kidsmath.presentation.viewmodel.impl.SplashViewModelImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val binding: FragmentSplashBinding by viewBinding(FragmentSplashBinding::bind)
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.openMenuScreen()
    }

}