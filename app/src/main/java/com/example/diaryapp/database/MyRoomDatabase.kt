package com.example.diaryapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.diaryapp.domain.Diary
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = [Diary::class], version = 8, exportSchema = false)
abstract class MyRoomDatabase : RoomDatabase() {

    abstract fun diaryDao(): DiaryDao

    companion object {

        @Volatile
        private var INSTANCE: MyRoomDatabase? = null

        fun getInstance(context: Context): MyRoomDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        val instance = Room.databaseBuilder(
                            context.applicationContext,
                            MyRoomDatabase::class.java,
                            "myRoomDatabase"
                        ).addCallback(MyRoomDataBasePopulator())
                            .fallbackToDestructiveMigration()
                            .build()
                        INSTANCE = instance
                    }
                }
            }

            return INSTANCE!!
        }
    }

    private class MyRoomDataBasePopulator : RoomDatabase.Callback() {

        private fun addItems() {

            INSTANCE?.let {
                GlobalScope.launch {
                    val diaryDao = it.diaryDao()
                    diaryDao.deleteAll()
                    diaryDao.insert(Diary(1, "My first post", "This is my first post in my new diary!", "17-01-2021", "", "", "Breda", 23, 122))
                    diaryDao.insert(Diary(2, "My second post", "This is my second post in my new diary!", "18-01-2021", "", "", "Bergen op Zoom", 10, 0))
                }
            }
        }

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            addItems()
        }

        override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
            super.onDestructiveMigration(db)

            addItems()
        }
    }
}