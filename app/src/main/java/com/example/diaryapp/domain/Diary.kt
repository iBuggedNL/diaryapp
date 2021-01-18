package com.example.diaryapp.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.diaryapp.R

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
    var weather: Int?
) {
    override fun toString(): String {
        return "$title ($date)"
    }

    fun getWeatherIcon(): Int {
        var weatherIcon = -1
        when(weather) {
            113 -> weatherIcon = R.drawable.ic_wi_day_sunny
            116, 119 -> weatherIcon = R.drawable.ic_wi_cloudy
            122 -> weatherIcon = R.drawable.ic_wi_day_sunny_overcast
            143, 248, 260 -> weatherIcon = R.drawable.ic_wi_fog
            185, 281, 284, 293, 296, 299, 302, 305, 308, 311 -> weatherIcon = R.drawable.ic_wi_day_rain
            179, 227, 230 -> weatherIcon = R.drawable.ic_wi_snow
            176, 263, 266 -> weatherIcon = R.drawable.ic_wi_day_sprinkle
            200 -> weatherIcon = R.drawable.ic_wi_day_lightning
            182 -> weatherIcon = R.drawable.ic_wi_day_sleet
            0 -> weatherIcon = android.R.color.transparent
        }

        return weatherIcon
    }
}
