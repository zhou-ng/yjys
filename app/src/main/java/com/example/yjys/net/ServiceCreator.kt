package com.example.yjys.net

import com.example.yjys.constant.STANDARD_MAP_URL
import com.example.yjys.constant.WEATHER_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

    private val retrofit = Retrofit.Builder()
        .baseUrl(WEATHER_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create():T = create(T::class.java)
}

object StandardMapServiceCreator {

    private val retrofit = Retrofit.Builder()
        .baseUrl(STANDARD_MAP_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)
}


/*
4）创建一个Retrofit构建器
*/