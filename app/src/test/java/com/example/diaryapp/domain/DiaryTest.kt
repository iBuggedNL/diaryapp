package com.example.diaryapp.domain

import com.example.diaryapp.R
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class DiaryTest {

    @Test
    fun `toString() of Diary`() {
        // Arrange
        val sut = Diary(
            1337,
            "My test post",
            "My test content",
            "18-01-2021",
            "",
            "",
            "Breda",
                5,
                122
        )
        val expected = "My test post (18-01-2021)"

        // Act
        val actual = sut.toString()

        // Assert
        assertEquals(expected, actual)
    }

    @Test
    fun `getWeatherIcon with present weatherCode`(){
        // Arrange
        val sut = Diary(
                1337,
                "My test post",
                "My test content",
                "18-01-2021",
                "",
                "",
                "Breda",
                5,
                122
        )
        val expected = R.drawable.ic_wi_day_sunny_overcast

        // Act
        val actual = sut.getWeatherIcon()

        // Assert
        assertEquals(expected, actual)
    }

    @Test
    fun `getWeatherIcon without present weatherCode`(){
        // Arrange
        val sut = Diary(
                1337,
                "My test post",
                "My test content",
                "18-01-2021",
                "",
                "",
                "Breda",
                5,
                0
        )
        val expected = android.R.color.transparent

        // Act
        val actual = sut.getWeatherIcon()

        // Assert
        assertEquals(expected, actual)
    }
}