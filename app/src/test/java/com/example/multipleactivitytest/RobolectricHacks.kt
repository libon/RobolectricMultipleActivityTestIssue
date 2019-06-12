package com.example.multipleactivitytest


import android.app.Activity
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.intent.Intents

object RobolectricHacks {
    fun launchNextActivity(scenario: ActivityScenario<out Activity>?): ActivityScenario<Activity>? {
        scenario?.moveToState(Lifecycle.State.CREATED)
        val intents = Intents.getIntents()
        return ActivityScenario.launch<Activity>(intents.last())
    }
}
