package com.example.diaryapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.diaryapp.domain.Diary

@Dao
interface DiaryDao {

    @Query("SELECT * FROM diary ORDER BY date")
    suspend fun getAll(): List<Diary>

    @Query("SELECT * FROM diary WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): Diary?

    @Insert
    suspend fun insert(diary: Diary)

    @Query("DELETE FROM diary")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(diary: Diary)
}
