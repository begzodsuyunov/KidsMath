package com.example.kidsmath.data.model

import android.content.Context
import android.media.MediaPlayer
import com.example.kidsmath.R

object Birds {

    var mediaPlayer = MediaPlayer()

    fun create(context: Context) {
        mediaPlayer = MediaPlayer.create(context, R.raw.sound_birds)
        mediaPlayer.start()
    }
}