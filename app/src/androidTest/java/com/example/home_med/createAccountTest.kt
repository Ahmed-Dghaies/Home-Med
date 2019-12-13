package com.example.home_med

import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
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

//    @Test
//    fun editUserInfo() {
//        onView(withId(R.id.profile_button)).perform(click())
//        onView(withId(R.id.edit_info)).perform(click())
//        onView(withId(R.id.user_email)).perform(click())
//        onView(withId(R.id.user_email)).perform(ViewActions.clearText(), ViewActions.typeText("gmail.com"))
//
//        onView(withId(R.id.user_first_name)).perform(click())
//        onView(withId(R.id.user_first_name)).perform(ViewActions.clearText(), ViewActions.typeText("Test"))
//
//        onView(withId(R.id.user_last_name)).perform(click())
//        onView(withId(R.id.user_last_name)).perform(ViewActions.clearText(), ViewActions.typeText("User"))
//
//        onView(withId(R.id.user_age)).perform(click())
//        onView(withId(R.id.user_age)).perform(ViewActions.clearText(), ViewActions.typeText("50"))
//
//        onView(withId(R.id.save)).perform(click())
//    }
}