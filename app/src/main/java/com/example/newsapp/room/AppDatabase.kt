package com.example.newsapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.models.News


@Database (entities = [News :: class], version = 1)


abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao() : NewsDao
}