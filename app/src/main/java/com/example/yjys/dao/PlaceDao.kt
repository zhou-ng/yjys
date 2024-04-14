package com.example.yjys.dao

import android.content.Context
import androidx.core.content.edit
import com.example.yjys.application.Application
import com.example.yjys.model.Place
import com.google.gson.Gson

object PlaceDao {

    fun savePlace(place: Place) {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() =
        Application.context.getSharedPreferences("sunny_weather", Context.MODE_PRIVATE)

}