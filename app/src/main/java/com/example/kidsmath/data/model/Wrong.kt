package com.example.kidsmath.data.model

import android.content.Context
import android.media.MediaPlayer
import com.example.kidsmath.R

object Wrong {

    var mediaPlayer = MediaPlayer()

    fun create(context: Context) {
        mediaPlayer = MediaPlayer.create(context, R.raw.wrong)
        mediaPlayer.start()
    }
}