package com.example.diaryapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.diaryapp.domain.Diary
import com.example.diaryapp.domain.DiaryRepository

class DiaryListVM(repository: DiaryRepository) : ViewModel() {
    val allPosts: LiveData<List<Diary>> = repository.getAll()
}

class DiaryListVMFactory(
    private val repository: DiaryRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        check(modelClass.isAssignableFrom(DiaryListVM::class.java))

        @Suppress("UNCHECKED_CAST")
        return DiaryListVM(repository) as T
    }
}