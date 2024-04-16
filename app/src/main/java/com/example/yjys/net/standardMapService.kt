package com.example.yjys.net

import com.example.yjys.model.StandardMapResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface standardMapService {
    @GET("earthdq-api/standardmap/page?pageNo={pageNo}")
    fun getPageData(@Path("pageNo") pageNo: Int): Call<StandardMapResponse>
}