

package com.example.home_med

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked
import android.widget.Checkable
import androidx.test.espresso.UiController
import org.hamcrest.BaseMatcher
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import org.hamcrest.CoreMatchers.isA
import org.hamcrest.CoreMatchers.not
import org.junit.runner.Description


@RunWith(AndroidJUnit4::class)

class LocalMedicationEspressoTesting {
    @get:Rule
    var mainActivity: ActivityTestRule<MainActivity>
        = ActivityTestRule(MainActivity::class.java)


    @Test
    fun viewLocalMedications() {
        onView(withId(R.id.localMedicationsButton)).perform(click())
    }

    @Test
    fun verifyMedicationInfo() {
        onView(withId(R.id.localMedicationsButton)).perform(click())
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
            hasDescendant(withText("Motrin")), click()))
        onView(withId(R.id.vm_medicationNameTitle)).check(matches(withText("Motrin")))
        onView(withId(R.id.vm_medicationQtyTitle)).check(matches(withText("50")))
        onView(withId(R.id.vm_medicationExpDateTitle)).check(matches(withText("12232019")))
        onView(withId(R.id.pillButton)).check(matches(isChecked()))
        onView(withId(R.id.mondayCheck)).check(matches(isChecked()))
        onView(withId(R.id.tuesdayCheck)).check(matches(isChecked()))
    }

    @Test
    fun updateMedicationInfo() {
        onView(withId(R.id.localMedicationsButton)).perform(click())
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
            hasDescendant(withText("Test")), click()))
        onView(withId(R.id.vm_medicationQtyTitle)).perform(ViewActions.clearText(), ViewActions.typeText("20"))
        onView(withId(R.id.vm_medicationExpDateTitle)).perform(ViewActions.clearText(), ViewActions.typeText("4/5/2020"))
        onView(withId(R.id.liquidButton)).perform(click())
        onView(withId(R.id.mondayCheck)).perform(scrollTo(), setChecked(true))
        onView(withId(R.id.tuesdayCheck)).perform(scrollTo(), setChecked(true))
        onView(withId(R.id.wednesdayCheck)).perform(scrollTo(), setChecked(true))
        onView(withId(R.id.updateMedicationBtn)).perform(scrollTo(), click())

        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
            hasDescendant(withText("Test")), click()))
        onView(withId(R.id.vm_medicationNameTitle)).check(matches(withText("Test")))
        onView(withId(R.id.vm_medicationQtyTitle)).check(matches(withText("20")))
        onView(withId(R.id.vm_medicationExpDateTitle)).check(matches(withText("4/5/2020")))
        onView(withId(R.id.liquidButton)).check(matches(isChecked()))
        onView(withId(R.id.mondayCheck)).check(matches(isChecked()))
        onView(withId(R.id.tuesdayCheck)).check(matches(isChecked()))
        onView(withId(R.id.wednesdayCheck)).check(matches(isChecked()))

        onView(withId(R.id.pillButton)).perform(click())
        onView(withId(R.id.mondayCheck)).perform(scrollTo(), setChecked(false))
        onView(withId(R.id.tuesdayCheck)).perform(scrollTo(), setChecked(false))
        onView(withId(R.id.wednesdayCheck)).perform(scrollTo(), setChecked(false))
        onView(withId(R.id.updateMedicationBtn)).perform(scrollTo(), click())
    }

    @Test
    fun medicationStatusTest() {
        onView(withId(R.id.localMedicationsButton)).perform(click())
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
            hasDescendant(withText("Test")), click()))
        onView(withId(R.id.deactivateMedicationBtn)).perform(click())

        onView(withId(R.id.recyclerviewInactive)).perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
            hasDescendant(withText("Test")), click()))
        onView(withId(R.id.activateMedicationBtn)).perform(click())

        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
            hasDescendant(withText("Test")), click()))
        onView(withId(R.id.deactivateMedicationBtn)).check(matches(withText("Deactivate Medication")))
    }

    fun setChecked(checked: Boolean): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): BaseMatcher<View> {
                return object : BaseMatcher<View>() {
                    override fun describeTo(description: org.hamcrest.Description?) {}
                    override fun matches(item: Any): Boolean {
                        return isA(Checkable::class.java).matches(item)
                    }
                    fun describeMismatch(item: Any, mismatchDescription: Description) {}
                    fun describeTo(description: Description) {}
                }
            }
            override fun getDescription(): String? {
                return null
            }
            override fun perform(uiController: UiController, view: View) {
                val checkableView = view as Checkable
                checkableView.isChecked = checked
            }
        }
    }
}