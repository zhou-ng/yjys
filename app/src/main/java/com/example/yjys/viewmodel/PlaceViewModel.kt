package com.example.yjys.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.yjys.model.Place
import com.example.yjys.net.Repository

class PlaceViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()
    val placeList = ArrayList<Place>()
    val placeLiveData = searchLiveData.switchMap { query ->
        Repository.searchPlaces(query)
    }
    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }

    fun savePlace(place: Place) = Repository.savePlace(place)
    fun getSavedPlace() = Repository.getSavedPlace()
    fun isPlaceSaved() = Repository.isPlaceSaved()
}