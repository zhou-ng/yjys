package com.example.yjys.model

import com.example.yjys.model.DailyResponse
import com.example.yjys.model.RealtimeResponse

class Weather(val realtime: RealtimeResponse.Realtime, val daily: DailyResponse.Daily)
