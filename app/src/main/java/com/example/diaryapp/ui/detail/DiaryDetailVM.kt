package com.example.diaryapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.diaryapp.domain.Diary
import com.example.diaryapp.domain.DiaryRepository

class DiaryDetailVM(
    repository: DiaryRepository,
    diaryId: Int
) : ViewModel() {
    val diary: LiveData<Diary> = repository.getById(diaryId)

}

class DiaryDetailVMFactory(
    private val repository: DiaryRepository,
    private val diaryId: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        check(modelClass.isAssignableFrom(DiaryDetailVM::class.java))

        @Suppress("UNCHECKED_CAST")
        return DiaryDetailVM(repository, diaryId) as T
    }
}