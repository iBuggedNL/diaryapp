package com.example.diaryapp.utils

import com.squareup.moshi.Json
import com.squareup.moshi.ToJson

data class WeatherStackItem(

	@Json(name="request")
	val request: Request,

	@Json(name="current")
	val current: Current,

	@Json(name="location")
	val location: Location
)

data class Request(

	@Json(name="unit")
	val unit: String,

	@Json(name="query")
	val query: String,

	@Json(name="language")
	val language: String,

	@Json(name="type")
	val type: String
)

data class Current(

	@Json(name="weather_descriptions")
	val weatherDescriptions: List<String>,

	@Json(name="observation_time")
	val observationTime: String,

	@Json(name="wind_degree")
	val windDegree: Int,

	@Json(name="visibility")
	val visibility: Int,

	@Json(name="weather_icons")
	val weatherIcons: List<String>,

	@Json(name="feelslike")
	val feelslike: Int,

	@Json(name="is_day")
	val isDay: String,

	@Json(name="wind_dir")
	val windDir: String,

	@Json(name="pressure")
	val pressure: Int,

	@Json(name="cloudcover")
	val cloudcover: Int,

	@Json(name="precip")
	val precip: Double,

	@Json(name="uv_index")
	val uvIndex: Int,

	@Json(name="temperature")
	val temperature: Int,

	@Json(name="humidity")
	val humidity: Int,

	@Json(name="wind_speed")
	val windSpeed: Int,

	@Json(name="weather_code")
	val weatherCode: Int
)

data class Location(

	@Json(name="localtime")
	val localtime: String,

	@Json(name="utc_offset")
	val utcOffset: String,

	@Json(name="country")
	val country: String,

	@Json(name="localtime_epoch")
	val localtimeEpoch: Int,

	@Json(name="name")
	val name: String,

	@Json(name="timezone_id")
	val timezoneId: String,

	@Json(name="lon")
	var lon: String,

	@Json(name="region")
	val region: String,

	@Json(name="lat")
	var lat: String
)

class WeatherAdapter {
	@ToJson
	fun newLocation(location: Location) : String = location.name

}

