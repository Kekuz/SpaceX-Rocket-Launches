package com.spacex_rocket_launches.util

import android.os.Handler
import android.os.Looper

class Debounce {

    private var isClickAllowed = true
    private val handler = Handler(Looper.getMainLooper())

    private companion object {
        const val CLICK_DEBOUNCE_DELAY = 1500L
    }


    fun clickDebounce(): Boolean {
        val current = isClickAllowed
        if (isClickAllowed) {
            isClickAllowed = false
            handler.postDelayed({ isClickAllowed = true }, CLICK_DEBOUNCE_DELAY)
        }
        return current
    }
}