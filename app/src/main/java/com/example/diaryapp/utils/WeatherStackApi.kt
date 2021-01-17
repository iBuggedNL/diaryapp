package com.example.diaryapp.utils

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val weatherStackbaseurl = "http://api.weatherstack.com/"

object WeatherStackApi {
    val moshi = Moshi.Builder()
        .add(WeatherAdapter())
        .add(KotlinJsonAdapterFactory())
        .build()

    val adapter: JsonAdapter<WeatherStackItem> = moshi.adapter(WeatherStackItem::class.java)

    val retrofit = Retrofit.Builder()
        .baseUrl(weatherStackbaseurl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val weatherStackService: WeatherStackService by lazy {
        retrofit.create(WeatherStackService::class.java)
    }
}
// http://api.weatherstack.com/current?access_key=7097696b78b919d0ff95d07e1c433a60&query=New%20York
interface WeatherStackService {
    @GET("current?")
    fun getResponse(
        @Query("access_key")access_key: String,
        @Query("query")query: String
    ) : Call<WeatherStackItem>
}


