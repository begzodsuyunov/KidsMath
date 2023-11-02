package com.example.kidsmath.presentation.screen.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.kidsmath.R
import com.example.kidsmath.data.model.BackMusic
import com.example.kidsmath.data.model.Birds
import com.example.kidsmath.data.shp.MySharedPreference
import com.example.kidsmath.presentation.navigator.NavigationHandler
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigationHandler: NavigationHandler

    private var currentApiVersion: Int = 0
    private val shp = MySharedPreference.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        title = "KotlinApp"
        currentApiVersion = Build.VERSION.SDK_INT

        val flags: Int =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY


        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {
            window.decorView.systemUiVisibility = flags
            val decorView: View = window.decorView
            decorView.setOnSystemUiVisibilityChangeListener { visibility ->
                if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN === 0) {
                    decorView.systemUiVisibility = flags
                }
            }
        }

        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)

        navigationHandler.navigationStack.onEach {
            it.invoke(fragment?.findNavController()!!)
        }.launchIn(lifecycleScope)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        if (currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
    }

    override fun onStart() {
        super.onStart()
        if (shp.music) {
            BackMusic.create(this)
            Birds.create(this)
            BackMusic.mediaPlayer.isLooping = true
            Birds.mediaPlayer.isLooping = true
        } else {
            BackMusic.mediaPlayer.pause()
            Birds.mediaPlayer.pause()
        }
    }

    override fun onPause() {
        super.onPause()
        BackMusic.mediaPlayer.pause()
        Birds.mediaPlayer.pause()
    }
}