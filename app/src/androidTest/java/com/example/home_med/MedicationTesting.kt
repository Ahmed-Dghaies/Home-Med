

package com.example.home_med

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class MedicationTesting {
    @get:Rule
    var mainActivity: ActivityTestRule<MainActivity>
        = ActivityTestRule(MainActivity::class.java)


    @Test
    fun viewLocalMedications() {
        onView(withId(R.id.localMedicationsButton)).perform(click())
    }
}