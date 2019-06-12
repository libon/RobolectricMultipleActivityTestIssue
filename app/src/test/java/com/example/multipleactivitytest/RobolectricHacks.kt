package com.example.multipleactivitytest


import android.app.Activity
import android.app.Application
import androidx.test.core.app.ApplicationProvider
import org.robolectric.Robolectric
import org.robolectric.Shadows

object RobolectricHacks {
    fun launchNextActivity() {
        val appContext: Application = ApplicationProvider.getApplicationContext()
        val nextActivity = Shadows.shadowOf(appContext).nextStartedActivity
        @Suppress("UNCHECKED_CAST") val controller =
            Robolectric.buildActivity<Activity>(
                Class.forName(nextActivity!!.component!!.className) as Class<Activity>,
                nextActivity
            )
        controller.create().start().resume().visible()
    }
}
