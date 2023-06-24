package com.example.android.marsphotos.sharePref

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
    fun saveString(key: String, value: String?) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String, defaultValue: String) : String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun removeString(key: String) {
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }
}