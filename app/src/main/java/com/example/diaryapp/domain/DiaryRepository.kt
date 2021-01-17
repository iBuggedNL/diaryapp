package com.example.diaryapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.diaryapp.database.DiaryDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class DiaryRepository(private val diaryDao: DiaryDao) {
    private val defaultPost = Diary(-1, "Loading posts...", "", "", "", "", "", 0, "Clear")

    private val all: MutableLiveData<List<Diary>> =
        MutableLiveData(listOf(defaultPost))

    fun getAll(): LiveData<List<Diary>> {
        GlobalScope.launch {
            all.postValue(diaryDao.getAll())
        }
        return all
    }

    fun getById(id: Int): LiveData<Diary> {
        val result = MutableLiveData(defaultPost)
        GlobalScope.launch {
            result.postValue(diaryDao.getById(id))
        }
        return result
    }

    fun add(diary: Diary) {
        GlobalScope.launch {
            diaryDao.insert(diary)
            getAll()
        }
    }

    fun remove(diary: Diary) {
        GlobalScope.launch {
            diaryDao.delete(diary)
            getAll()
        }
    }
}