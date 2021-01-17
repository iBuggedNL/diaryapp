package com.example.diaryapp.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.diaryapp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testNavigateOnClickList() {
        onView(withText("The Shawshank Redemption"))
            .perform(click())

        onView(withText("F. Darabont"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testAddMovieAndVerifyThatItAppearsInList() {
        val uniqueName = LocalDateTime.now().toString()

        // Navigate to add fragment from listview
        onView(withId(R.id.addButton))
            .perform(click())

        // Enter movie details
        onView(withId(R.id.addTitleInput))
            .perform(typeText(uniqueName))

        onView(withId(R.id.addDirectorInput))
            .perform(typeText("Test Director"))

        onView(withId(R.id.addReleaseInput))
            .perform(typeText("aa1234"))

        // Submit new movie
        onView(withId(R.id.submitButton))
            .perform(click())

        // Confirm that the new movie is in the list view
        onView(withText(uniqueName))
            .check(matches(isDisplayed()))
    }
}