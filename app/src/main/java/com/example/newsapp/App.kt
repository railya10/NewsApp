package com.example.newsapp

import android.app.Application
import androidx.room.Room
import com.example.newsapp.room.AppDatabase

class App : Application() {

    companion object {
        lateinit var database: AppDatabase
    }


    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }
}
