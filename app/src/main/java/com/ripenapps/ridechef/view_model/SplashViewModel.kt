package com.ripenapps.ridechef.view_model

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

class SplashViewModel : ViewModel() {

    private val timer: CountDownTimer

    private var _splashStatus = MutableLiveData(false)

    val splashStatus: LiveData<Boolean>
        get() = _splashStatus

    init {
        _splashStatus.value = false

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                _splashStatus.value = true
            }

        }

        timer.start()
    }

    override fun onCleared() {
        super.onCleared()
        println("onCleared")
        timer.cancel()
    }

    companion object {
        // Time when the over
        private const val DONE = 0L

        // Countdown time interval
        private const val ONE_SECOND = 1000L

        // Total time for the game
        private const val COUNTDOWN_TIME = 3000L
    }
}