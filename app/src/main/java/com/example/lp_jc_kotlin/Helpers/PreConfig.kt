package com.example.lp_jc_kotlin.Helpers

import android.content.Context
import android.net.Uri
import android.preference.PreferenceManager
import com.example.lp_jc_kotlin.Model.AllRecipe
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.util.*

object PreConfig {
    private const val LIST_KEY = "list_Key"
    fun writeListInPref(context: Context?, list: List<AllRecipe?>?) {
        val gson = GsonBuilder()
                .registerTypeAdapter(Uri::class.java, UriInOut())
                .create()
        val jsonString = gson.toJson(list)
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        editor.putString(LIST_KEY, jsonString)
        editor.apply()
    }

    fun readListFromPref(context: Context?): MutableList<AllRecipe>? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val jsonString = prefs.getString(LIST_KEY, "")
        val gson = GsonBuilder()
                .registerTypeAdapter(Uri::class.java, UriInOut())
                .create()
        val type = object : TypeToken<ArrayList<AllRecipe?>?>() {}.type
        return gson.fromJson(jsonString, type)
    }
}