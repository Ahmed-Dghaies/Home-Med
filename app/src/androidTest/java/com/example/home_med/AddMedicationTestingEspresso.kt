package com.example.home_med

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class AddMedicationTestingEspresso {

    @get:Rule
    var mainActivity: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkInputValidationMedName() {
        onView(withId(R.id.localMedicationsButton)).perform(click())
        onView(withId(R.id.addMedicationButton)).perform(click())
        onView(withId(R.id.vm_medicationQtyTitle)).perform(click())
        onView(withId(R.id.vm_medicationQtyTitle)).perform(replaceText("5"))
        onView(withId(R.id.vm_medicationExpDateTitle)).perform(replaceText("11/23/2019"))
        onView(withId(R.id.liquidButton)).perform(click())
        onView(withId(R.id.scrollAdd)).perform(ViewActions.swipeUp())
        onView(withId(R.id.saveMedicationButton)).perform(click())
        onView(withId(R.id.vm_medicationNameTitle)).check(matches(withText("")))
    }

    @Test
    fun checkInputValidationMedQty() {
        onView(withId(R.id.localMedicationsButton)).perform(click())
        onView(withId(R.id.addMedicationButton)).perform(click())
        onView(withId(R.id.vm_medicationNameTitle)).perform(replaceText("Test"))
        onView(withId(R.id.vm_medicationExpDateTitle)).perform(replaceText("11/23/2019"))
        onView(withId(R.id.liquidButton)).perform(click())
        onView(withId(R.id.scrollAdd)).perform(ViewActions.swipeUp())
        onView(withId(R.id.saveMedicationButton)).perform(click())
        onView(withId(R.id.vm_medicationQtyTitle)).check(matches(withText("")))
    }

    @Test
    fun checkInputValidationMedExpData() {
        onView(withId(R.id.localMedicationsButton)).perform(click())
        onView(withId(R.id.addMedicationButton)).perform(click())
        onView(withId(R.id.vm_medicationNameTitle)).perform(replaceText("Test"))
        onView(withId(R.id.vm_medicationQtyTitle)).perform(replaceText("5"))
        onView(withId(R.id.liquidButton)).perform(click())
        onView(withId(R.id.scrollAdd)).perform(ViewActions.swipeUp())
        onView(withId(R.id.saveMedicationButton)).perform(click())
        onView(withId(R.id.vm_medicationExpDateTitle)).check(matches(withText("")))
    }

    @Test
    fun checkInputValidationMedType() {
        onView(withId(R.id.localMedicationsButton)).perform(click())
        onView(withId(R.id.addMedicationButton)).perform(click())
        onView(withId(R.id.vm_medicationExpDateTitle)).perform(replaceText("11/23/2019"))
        onView(withId(R.id.vm_medicationNameTitle)).perform(replaceText("Test"))
        onView(withId(R.id.vm_medicationQtyTitle)).perform(replaceText("5"))
        onView(withId(R.id.scrollAdd)).perform(ViewActions.swipeUp())
        onView(withId(R.id.saveMedicationButton)).perform(click())
    }

    @Test
    fun addLiquidMedication() {
        val rand = (0..1000).random()
        onView(withId(R.id.localMedicationsButton)).perform(click())
        onView(withId(R.id.addMedicationButton)).perform(click())
        onView(withId(R.id.vm_medicationExpDateTitle)).perform(replaceText("11/23/2019"))
        onView(withId(R.id.vm_medicationNameTitle)).perform(replaceText("TestLiquid"))
        onView(withId(R.id.vm_medicationQtyTitle)).perform(replaceText("5"))
        onView(withId(R.id.liquidButton)).perform(click())
        onView(withId(R.id.scrollAdd)).perform(ViewActions.swipeUp())
        onView(withId(R.id.saveMedicationButton)).perform(click())
        onView(withId(R.id.localMedicationTityle)).check(matches(withText("Local Medication")))
    }

    @Test
    fun addLPillMedication() {
        val rand = (0..1000).random()
        onView(withId(R.id.localMedicationsButton)).perform(click())
        onView(withId(R.id.addMedicationButton)).perform(click())
        onView(withId(R.id.vm_medicationExpDateTitle)).perform(replaceText("11/23/2019"))
        onView(withId(R.id.vm_medicationNameTitle)).perform(replaceText("TestPill"))
        onView(withId(R.id.vm_medicationQtyTitle)).perform(replaceText("5"))
        onView(withId(R.id.pillButton)).perform(click())
        onView(withId(R.id.scrollAdd)).perform(ViewActions.swipeUp())
        onView(withId(R.id.saveMedicationButton)).perform(click())
        onView(withId(R.id.localMedicationTityle)).check(matches(withText("Local Medication")))
    }

    @Test
    fun addLPillMedicationWithDays() {
        val rand = (0..1000).random()
        onView(withId(R.id.localMedicationsButton)).perform(click())
        onView(withId(R.id.addMedicationButton)).perform(click())
        onView(withId(R.id.vm_medicationExpDateTitle)).perform(replaceText("11/23/2019"))
        onView(withId(R.id.vm_medicationNameTitle)).perform(replaceText("TestDays"))
        onView(withId(R.id.vm_medicationQtyTitle)).perform(replaceText("5"))
        onView(withId(R.id.pillButton)).perform(click())
        onView(withId(R.id.mondayCheck)).perform(click())
        onView(withId(R.id.tuesdayCheck)).perform(click())
        onView(withId(R.id.wednesdayCheck)).perform(click())
        onView(withId(R.id.scrollAdd)).perform(ViewActions.swipeUp())
        onView(withId(R.id.saveMedicationButton)).perform(click())
        onView(withId(R.id.localMedicationTityle)).check(matches(withText("Local Medication")))
    }
}