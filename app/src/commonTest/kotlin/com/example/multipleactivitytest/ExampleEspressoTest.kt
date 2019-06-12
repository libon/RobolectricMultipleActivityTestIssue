package com.example.multipleactivitytest


import android.app.Activity
import android.app.Application
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleEspressoTest {
    /**
     * Launch the main activity, tap on the button, and verify we're in the second activity
     */
    @Test
    fun testMultiActivity() {
        Intents.init()
        val appContext: Application = ApplicationProvider.getApplicationContext()
        val scenario: ActivityScenario<out Activity>? = launch(MainActivity::class.java)
        onView(withId(R.id.button1)).perform(click())
        RobolectricHacks.launchNextActivity(scenario)
        onView(withId(R.id.text2)).check(
            matches(
                allOf(
                    withText(appContext.getString(R.string.text2)),
                    isDisplayed()
                )
            )
        )
    }
}
