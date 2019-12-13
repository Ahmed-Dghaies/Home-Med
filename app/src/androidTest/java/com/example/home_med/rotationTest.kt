package com.example.home_med

import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class rotationTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun activityMainRotationTest() {
        rotateScreenLandscape()
        rotateScreenPortrait()
    }

    @Test
    fun localMedicationRotationTest() {
        onView(withId(R.id.localMedicationsButton)).perform(click())
        rotateScreenLandscape()
        rotateScreenPortrait()
    }

    @Test
    fun addMedicationRotationTest() {
        onView(withId(R.id.localMedicationsButton)).perform(click())
        onView(withId(R.id.addMedicationButton)).perform(click())
//        rotateScreenLandscape()
//        rotateScreenPortrait()
    }

    @Test
    fun viewMedicationRotationTest() {
        onView(withId(R.id.localMedicationsButton)).perform(click())
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        //rotateScreenLandscape()
        //rotateScreenPortrait()
    }

    @Test
    fun userInfoRotationTest() {
        onView(withId(R.id.profile_button)).perform(click())
//        rotateScreenLandscape()
//        rotateScreenPortrait()
    }

    @Test
    fun editUserInfoRotationTest() {
        onView(withId(R.id.profile_button)).perform(click())
        onView(withId(R.id.edit_info)).perform(click())
        rotateScreenLandscape()
        rotateScreenPortrait()
    }

    private fun rotateScreenPortrait() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val orientation = context.resources.configuration.orientation

        val activity = activityRule.activity
        activity.requestedOrientation = if (orientation == Configuration.ORIENTATION_PORTRAIT)
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        else
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    private fun rotateScreenLandscape() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val orientation = context.resources.configuration.orientation

        val activity = activityRule.activity
        activity.requestedOrientation = if (orientation == Configuration.ORIENTATION_LANDSCAPE)
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        else
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }
}