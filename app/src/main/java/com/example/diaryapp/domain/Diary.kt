package com.example.diaryapp.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Diary(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String,
    var content: String?,
    var date: String?,
    var coordLat: String?,
    var coordLong: String?,
    var city: String?,
    var temperature: Int? = -99,
    var weather: String?
) {
    override fun toString(): String {
        return "$title ($date)"
    }
}
