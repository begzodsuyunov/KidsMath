package com.example.kidsmath.utils

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import com.example.kidsmath.presentation.screen.activity.MainActivity

object Constants {


    fun goToPlayMarket(activity: MainActivity) {
        try {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://payme.uz")
                )
            )
        } catch (e: ActivityNotFoundException) {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://payme.uz")
                )
            )
        }
    }
}