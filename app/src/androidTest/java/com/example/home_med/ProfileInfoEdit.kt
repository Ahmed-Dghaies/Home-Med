package com.example.home_med


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProfileInfoEdit {
    @get:Rule
    var mainActivity: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    lateinit var firebaseAuth: FirebaseAuth

    @Test
    fun edit_profile() {

        firebaseAuth = FirebaseAuth.getInstance()
        var user: FirebaseUser? = firebaseAuth.getCurrentUser()
        val auth = AuthenticationTest()
        val user_first_name: String = "Ahmed"
        val user_last_name: String = "Dghaies"
        val user_age: String = "23"

        if (user == null) {
            auth.login("authenticationTest@gmail.com", "Test123!")
        }
        onView(withId(R.id.home_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.profile_button)).perform(click())
        onView(withId(R.id.profile_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.edit_info)).perform(click())
        onView(withId(R.id.edit_profile_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.user_first_name)).perform(clearText(), typeText(user_first_name))
        onView(withId(R.id.user_last_name)).perform(clearText(), typeText(user_last_name))
        onView(withId(R.id.user_age)).perform(clearText(), typeText(user_age))
        onView(withId(R.id.save)).perform(click())
        onView(withId(R.id.profile_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.user_first_name)).check(matches(withText(containsString(user_first_name))))
        onView(withId(R.id.user_last_name)).check(matches(withText(containsString(user_last_name))))
        onView(withId(R.id.user_age)).check(matches(withText(containsString(user_age))))

    }
}