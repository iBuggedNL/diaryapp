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
import java.util.*

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testNavigationToDetailView() {
        onView(withText("My first post"))
            .perform(click())

        onView(withText("This is my first post in my new diary!"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testCreateNewPostWithoutLocationEnabled() {
        val postTitle = UUID.randomUUID().toString()
        val postContent = "This is my test description!"

        // Click on the add button in view
        onView(withId(R.id.addButton))
            .perform(click())

        // Fill in the title
        onView(withId(R.id.addPostTitleInput))
            .perform(typeText(postTitle))

        // Fill in the content
        onView(withId(R.id.addPostContentInput))
            .perform(typeText(postContent))

        // Submit post
        onView(withId(R.id.submitButton))
            .perform(click())

        // Check if the post is added
        onView(withText(postTitle))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testCreateNewPostWithLocationEnabled() {
        val postTitle = UUID.randomUUID().toString()
        val postContent = "This is my second test description!"

        // Click on the add button in view
        onView(withId(R.id.addButton))
                .perform(click())

        // Fill in the title
        onView(withId(R.id.addPostTitleInput))
                .perform(typeText(postTitle))

        // Fill in the content
        onView(withId(R.id.addPostContentInput))
                .perform(typeText(postContent))

        // Toggle location switch
        onView(withId(R.id.locationSwitch))
                .perform(click())

        // Submit post
        onView(withId(R.id.submitButton))
                .perform(click())

        // Check if the post is added
        onView(withText(postTitle))
                .check(matches(isDisplayed()))
    }
}