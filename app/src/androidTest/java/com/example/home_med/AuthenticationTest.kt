package com.example.home_med

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AuthenticationTest {

    @get:Rule
    var mainActivity: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    lateinit var firebaseAuth: FirebaseAuth

    fun login(email: String, password: String) {

        var user_email: String = email
        var user_password: String = password
        firebaseAuth = FirebaseAuth.getInstance()

        onView(withId(R.id.login_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextEmail)).perform(clearText(), typeText(user_email))
        onView(withId(R.id.editTextPassword)).perform(clearText(), typeText(user_password))
        onView(withId(R.id.loginButton)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.home_layout)).check(matches(isDisplayed()))
        var user: FirebaseUser? = firebaseAuth.getCurrentUser()
        user!!.email.toString().equals(user_email)
    }

    fun register(email: String, password: String) {

        var user_email: String = email
        var user_password: String = password
        var user_password_confirmation: String = password
        firebaseAuth = FirebaseAuth.getInstance()
        var user: FirebaseUser? = firebaseAuth.getCurrentUser()


        if (user != null) {
            logout()
        }
        onView(withId(R.id.login_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.login_page_text)).perform(click())
        onView(withId(R.id.register_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextEmail)).perform(clearText(), typeText(user_email))
        onView(withId(R.id.editTextPassword)).perform(clearText(), typeText(user_password))
        onView(withId(R.id.editTextPasswordConfirmation)).perform(clearText(), typeText(user_password_confirmation))
        onView(withId(R.id.registerButton)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.home_layout)).check(matches(isDisplayed()))


        user!!.email.toString().equals(user_email)

    }

    fun logout() {

        //onView(withId(R.id.home_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.profile_button)).perform(click())
        //onView(withId(R.id.profile_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.logout_button)).perform(click())
        Thread.sleep(2000)
        //onView(withId(R.id.login_layout)).check(matches(isDisplayed()))

    }

    @Test
    fun authentication_scenario() {
        val rand = (0..1000).random().toString()
        var user_email: String = rand + "authenticationTest@gmail.com"
        var user_password: String = "Test123!"

        register(user_email,user_password)
        logout()
        login(user_email,user_password)
        //logout()
    }
}