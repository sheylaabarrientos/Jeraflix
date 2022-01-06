package com.jera.jeraflixx.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

fun Fragment.setToolbar(toolbar: Toolbar) {
    val supportActivity = activity
    if (supportActivity is AppCompatActivity) {
        with(supportActivity) { setSupportActionBar(toolbar) }
    }
}