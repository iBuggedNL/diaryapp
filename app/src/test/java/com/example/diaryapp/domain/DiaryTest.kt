package com.example.diaryapp.domain

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class DiaryTest {

    @Test
    fun `deze test die draai ik omdat ik wil testen of de toString van Movie werkt`() {
        // Arrange
        val sut = Diary(
            1337,
            "My test post",
            "My test content",
            "",
            "",
            ""
        )
        val expected = "Wat een mooie film (1994)"

        // Act
        val actual = sut.toString()

        // Assert
        assertEquals(expected, actual)
    }
}