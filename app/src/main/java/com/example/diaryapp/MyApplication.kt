package com.example.diaryapp

import android.app.Application
import com.example.diaryapp.database.MyRoomDatabase
import com.example.diaryapp.domain.DiaryRepository

class MyApplication : Application() {
    val database by lazy {  MyRoomDatabase.getInstance(this) }
    val diaryRepository by lazy { DiaryRepository(database.diaryDao()) }
}