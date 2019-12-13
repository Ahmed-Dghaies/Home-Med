package com.example.home_med

import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
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
class createAccountTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testValidAccount() {
        onView(withId(R.id.login_page_text)).perform(click())
        onView(withId(R.id.editTextEmail)).perform(click())
        onView(withId(R.id.editTextEmail)).perform(replaceText("tester"))

        onView(withId(R.id.registerButton)).perform(click())
        onView(withId(R.id.editTextPassword)).perform(click())

        onView(withId(R.id.editTextPassword)).perform(replaceText("123xyz"))
        onView(withId(R.id.registerButton)).perform(click())

        onView(withId(R.id.editTextPassword)).perform(replaceText(""))
        onView(withId(R.id.registerButton)).perform(click())

        onView(withId(R.id.editTextPasswordConfirmation)).perform(click())
        onView(withId(R.id.editTextPasswordConfirmation)).perform(replaceText("123xyz"))
        onView(withId(R.id.registerButton)).perform(click())
    }

    @Test
    fun createAccount() {
        val rand = (0..1000).random()
        onView(withId(R.id.login_page_text)).perform(click())
        onView(withId(R.id.editTextEmail)).perform(click())
        onView(withId(R.id.editTextEmail)).perform(replaceText("test@my.com" + rand))
        onView(withId(R.id.editTextPassword)).perform(click())
        onView(withId(R.id.editTextPassword)).perform(replaceText("123xyz"))
        onView(withId(R.id.editTextPasswordConfirmation)).perform(click())
        onView(withId(R.id.editTextPasswordConfirmation)).perform(replaceText("123xyz"))
        onView(withId(R.id.registerButton)).perform(click())
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