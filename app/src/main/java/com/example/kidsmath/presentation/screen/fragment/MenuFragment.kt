package com.example.kidsmath.presentation.screen.fragment

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kidsmath.R
import com.example.kidsmath.data.model.BackMusic
import com.example.kidsmath.data.model.Birds
import com.example.kidsmath.data.shp.MySharedPreference
import com.example.kidsmath.databinding.DialogInfoBinding
import com.example.kidsmath.databinding.DilaogLevelBinding
import com.example.kidsmath.databinding.DilaogSettingBinding
import com.example.kidsmath.databinding.FragmentMenuBinding
import com.example.kidsmath.presentation.screen.activity.MainActivity
import com.example.kidsmath.presentation.viewmodel.MenuViewModel
import com.example.kidsmath.presentation.viewmodel.impl.MenuViewModelImpl
import com.example.kidsmath.utils.Constants
import com.example.kidsmath.utils.onClick
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MenuFragment : Fragment(R.layout.fragment_menu) {

    private val binding: FragmentMenuBinding by viewBinding(FragmentMenuBinding::bind)
    private val viewModel: MenuViewModel by viewModels<MenuViewModelImpl>()
    private lateinit var level: String
    private val shp = MySharedPreference.getInstance()

    override fun onPause() {
        super.onPause()
        lifecycleScope.launch {
            viewModel.setLevel(level)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.showSettingButton.onEach {
            if (it) {
                showSettingDialog()
            }
        }.launchIn(lifecycleScope)


        viewModel.showLevelButton.onEach {
            if (it) {
                showLevelDialog()
            }
        }.launchIn(lifecycleScope)

        viewModel.showExitButton.onEach {
            if (it) {
                val a = Intent(Intent.ACTION_MAIN)
                a.addCategory(Intent.CATEGORY_HOME)
                a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(a)
            }
        }.launchIn(lifecycleScope)

        viewModel.showInfoButton.onEach {
            if (it) {
                showInfoDialog()
            }
        }.launchIn(lifecycleScope)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.levelFlow.collectLatest {
                level = it
            }
        }

        binding.playNow.setOnClickListener {
            it.onClick {
                viewModel.onCLickPlayNowButton(level)
            }
        }

        if (view != null) {
            binding.share.setOnClickListener {
                it.onClick {
                    ShareCompat.IntentBuilder.from(requireActivity())
                        .setType("text/plain")
                        .setChooserTitle("Chooser title")
                        .setText("http://play.google.com/store/apps/details?id=" + activity?.packageName)
                        .startChooser()
                }
            }
        }
        binding.donate.setOnClickListener {
            it.onClick {
                Constants.goToPlayMarket(activity as MainActivity)
            }
        }

        binding.level.setOnClickListener {
            it.onClick {
                viewModel.onClickLevelButton()
            }
        }
        binding.setting.setOnClickListener {
            it.onClick {
                viewModel.onClickSettingButton()
            }
        }
        binding.info.setOnClickListener {
            it.onClick {
                viewModel.onClickInfoButton()
            }
        }
        binding.exit.setOnClickListener {
            it.onClick {
                viewModel.onClickExitButton()
            }
        }


    }


    private fun showSettingDialog() {

        val dialog = AlertDialog.Builder(requireContext()).create()
        val dialogBinding =
            DilaogSettingBinding.inflate(LayoutInflater.from(requireContext()), null, false)

        dialog.setCancelable(false)

        dialog.window!!.setBackgroundDrawable(
            ColorDrawable(
                Color.TRANSPARENT
            )
        )
        dialogBinding.shuffle.setOnClickListener {
            it.onClick {
                dialogBinding.shuffle.isClickable = false
                dialogBinding.shuffle.alpha = 0.5f
                viewModel.generate()
                Toast.makeText(
                    requireContext(),
                    "All level questions have been regenerated",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        dialogBinding.musicSwitch.isChecked = BackMusic.mediaPlayer.isPlaying
        dialogBinding.musicSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                BackMusic.create(requireContext())
                Birds.create(requireContext())
                BackMusic.mediaPlayer.isLooping = true
                Birds.mediaPlayer.isLooping = true
                shp.music = true
            } else {
                shp.music = false
                BackMusic.mediaPlayer.pause()
                Birds.mediaPlayer.pause()
            }
        }
        dialogBinding.musicSwitch.isChecked = shp.music


        dialogBinding.vibrationSwitch.isChecked = shp.vibration
        dialogBinding.vibrationSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            shp.vibration = isChecked
        }
        dialogBinding.vibrationSwitch.isChecked = shp.vibration

        dialogBinding.ok.setOnClickListener {
            it.onClick {
                Log.d("JJJ", "Salom")
                dialog.dismiss()
            }
        }

        dialog.setView(dialogBinding.root)
        dialog.show()
    }

    private fun showInfoDialog() {
        val dialog = AlertDialog.Builder(requireContext()).create()
        val dialogBinding =
            DialogInfoBinding.inflate(LayoutInflater.from(requireContext()), null, false)

        dialog.setCancelable(false)

        dialog.window!!.setBackgroundDrawable(
            ColorDrawable(
                Color.TRANSPARENT
            )
        )

        dialogBinding.yes.setOnClickListener {
            it.onClick {
                dialog.dismiss()
            }
        }

        dialog.setView(dialogBinding.root)
        dialog.show()
    }

    private fun showLevelDialog() {

        val dialog = AlertDialog.Builder(requireContext()).create()
        val dialogBinding =
            DilaogLevelBinding.inflate(LayoutInflater.from(requireContext()), null, false)

        dialog.setCancelable(false)

        dialog.window!!.setBackgroundDrawable(
            ColorDrawable(
                Color.TRANSPARENT
            )
        )

        when (level) {
            "easy" -> {
                dialogBinding.easy.alpha = 0.5f
                dialogBinding.easy.isClickable = false
                dialogBinding.medium.alpha = 1f
                dialogBinding.medium.isClickable = true
                dialogBinding.hard.alpha = 1f
                dialogBinding.hard.isClickable = true
            }

            "medium" -> {
                dialogBinding.medium.alpha = 0.5f
                dialogBinding.medium.isClickable = false
                dialogBinding.easy.alpha = 1f
                dialogBinding.easy.isClickable = true
                dialogBinding.hard.alpha = 1f
                dialogBinding.hard.isClickable = true
            }

            "hard" -> {
                dialogBinding.hard.alpha = 0.5f
                dialogBinding.hard.isClickable = false
                dialogBinding.medium.alpha = 1f
                dialogBinding.medium.isClickable = true
                dialogBinding.easy.alpha = 1f
                dialogBinding.easy.isClickable = true
            }
        }

        dialogBinding.easy.setOnClickListener {
            it.onClick {
                viewModel.onClickEasy(level)
                dialogBinding.easy.alpha = 0.5f
                dialogBinding.easy.isClickable = false
                dialogBinding.medium.alpha = 1f
                dialogBinding.medium.isClickable = true
                dialogBinding.hard.alpha = 1f
                dialogBinding.hard.isClickable = true
            }
        }
        dialogBinding.medium.setOnClickListener {
            it.onClick {
                viewModel.onClickMedium(level)
                dialogBinding.medium.alpha = 0.5f
                dialogBinding.medium.isClickable = false
                dialogBinding.easy.alpha = 1f
                dialogBinding.easy.isClickable = true
                dialogBinding.hard.alpha = 1f
                dialogBinding.hard.isClickable = true
            }
        }

        dialogBinding.hard.setOnClickListener {
            it.onClick {
                viewModel.onClickHard(level)
                dialogBinding.hard.alpha = 0.5f
                dialogBinding.hard.isClickable = false
                dialogBinding.medium.alpha = 1f
                dialogBinding.medium.isClickable = true
                dialogBinding.easy.alpha = 1f
                dialogBinding.easy.isClickable = true
            }
        }
        dialogBinding.ok.setOnClickListener {
            it.onClick {
                lifecycleScope.launch {
                    viewModel.setLevel(level)
                }
                dialog.cancel()
            }
        }

        dialog.setView(dialogBinding.root)
        dialog.show()
    }
}