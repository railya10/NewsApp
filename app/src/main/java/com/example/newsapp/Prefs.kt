package com.example.newsapp

import android.content.Context
import android.text.Editable
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.edit

class Prefs(context: Context) {

    private val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun saveState() {
        preferences.edit().putBoolean("isShown", true).apply()
    }

    fun isShown(): Boolean {
        return preferences.getBoolean("isShown", false)
    }

    fun textWatcher(p0: Editable?) {
        preferences.edit().putString("name", p0.toString()).apply()
    }

    fun getTextWatcher(): String? {
        return preferences.getString("name", "")
    }
}




