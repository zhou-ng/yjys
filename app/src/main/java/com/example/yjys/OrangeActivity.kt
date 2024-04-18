package com.example.yjys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yjys.adapter.OrangeAdapter
import com.example.yjys.entity.Favorite
import com.example.yjys.model.DailyResponse
import com.example.yjys.model.DailyResponse.Companion.TYPE_HOR
import com.example.yjys.model.DailyResponse.Companion.TYPE_VTL
import kotlinx.android.synthetic.main.activity_orange.orange_rv
import kotlinx.android.synthetic.main.fragment_place.recyclerView
import java.util.Date

class OrangeActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: OrangeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orange)
        recyclerView = findViewById(R.id.orange_rv)
        // 设置 RecyclerView 的布局管理器
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        // 获取天气数据
        val weatherData = getWeatherData()
        // 创建 OrangeAdapter 并设置到 RecyclerView 上
        adapter = OrangeAdapter(weatherData)
        recyclerView.adapter = adapter
    }
    private fun getWeatherData(): List<DailyResponse.CustomWeather> {
        val customWeatherList = mutableListOf<DailyResponse.CustomWeather>()

        for (i in 0 until 4) {
            // 创建温度数据
            val temperature = DailyResponse.Temperature(
                max = 25.0f,
                min = 15.0f
            )
            // 创建天气状况数据
            val skycon = DailyResponse.Skycon(
                value = "CLEAR_DAY",
                date = Date(2024 - 1900, 3, 18 + i)
            )
            // 根据索引确定布局类型
            val type = if (i < 2) DailyResponse.TYPE_HOR else DailyResponse.TYPE_VTL
            val customWeather = DailyResponse.CustomWeather(
                temperature = temperature,
                skycon = skycon,
                type = type
            )
            // 将 CustomWeather 对象添加到列表中
            customWeatherList.add(customWeather)
        }
        return customWeatherList
    }
}