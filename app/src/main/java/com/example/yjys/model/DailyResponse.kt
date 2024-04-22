package com.example.yjys.model

import com.google.gson.annotations.SerializedName
import java.util.*

class DailyResponse(val status: String, val result: Result) {
    companion object {
        const val TYPE_TOP = 0
        const val TYPE_CENTER = 1
        const val TYPE_BOTTOM = 2
    }
    class Result(val daily: Daily)
    class Daily(val temperature: List<Temperature>, val skycon: List<Skycon>, @SerializedName("life_index") val lifeIndex: LifeIndex)
    class Temperature(val max: Float, val min: Float)
    class Skycon(val value: String, val date: Date)
    class LifeIndex(val coldRisk: List<LifeDescription>, val carWashing: List<LifeDescription>, val ultraviolet: List<LifeDescription>, val dressing: List<LifeDescription>)
    class LifeDescription(val desc: String)

    data class CustomWeather(
        val temperature: Temperature,
        val skycon: Skycon,
        val type: Int
    )
}