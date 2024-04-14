package com.example.yjys.net

import com.example.yjys.application.Application
import com.example.yjys.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {
    @GET("v2/place?token=${Application.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String) : Call<PlaceResponse>
}