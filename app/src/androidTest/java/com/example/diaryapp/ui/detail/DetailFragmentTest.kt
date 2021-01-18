package com.example.diaryapp.ui.detail

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.diaryapp.ui.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun before() {
        activityRule.scenario.onActivity { it.showDetail(1) }
    }

    @Test
    fun checkIfTemperatureIsShown() {
        onView(withText("Temperatuur: 23 graden"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkIfDescriptionIsShown() {
        onView(withText("This is my first post in my new diary!"))
                .check(matches(isDisplayed()))
    }

    @Test
    fun checkIfDateIsShown() {
        onView(withText("18-01-2021"))
                .check(matches(isDisplayed()))
    }
}
