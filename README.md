Sample project to demonstrate multi-activity espresso/robolectric issue

This project provides an app with two screens:
* `MainActivity`: contains a button which when clicked, opens `SecondScreenActivity`
* `SecondScreenActivity`: contains a text which says "You're in screen 2"

The project contains an espresso test which:
* Launches `MainActivity` with the `ActivityScenario` api.
* Taps on the button
* Verifies that the text "You're in screen 2" appears.

The espresso test passes when run on an emulator or device: `./gradlew cAT`

The espresso test fails when run on the jvm/robolectric: `./gradlew testDebugUnitTest`:

```
androidx.test.espresso.NoMatchingViewException: No views in hierarchy found matching: with id: com.example.multipleactivitytest:id/text2
```

A hacky workaround to allow the test to pass on emulator/device and jvm/robolectric is to create a `RobolectricHacks.launchNextActivity()` function:
* On jvm/robolectric only (`test` sourceSet): add a few lines with the Robolectric api to launch the next activity:
  - Get the next activity intent via `ShadowContextWrapper.getNextStartedActivity()`
  - Extract the class name from this intent
  - Use the `Robolectric.buildActivity()` api to launch this new activity
* On emulator/device (`androidTest` sourceSet): provide a dummy implementation.
* In the espresso test (in `commonTest` sourceSet), call `RobolectricHacks.launchNextActivity()` after the button click, and before the text view verification.

