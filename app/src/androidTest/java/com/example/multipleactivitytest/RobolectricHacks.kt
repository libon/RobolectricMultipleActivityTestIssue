package com.example.multipleactivitytest

import android.app.Activity
import androidx.test.core.app.ActivityScenario

object RobolectricHacks {
    fun launchNextActivity(scenario: ActivityScenario<out Activity>?): ActivityScenario<Activity>? = null
}
