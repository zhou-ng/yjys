package com.example.yjys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yjys.adapter.PictureAdapter
import com.example.yjys.model.DailyResponse
import kotlinx.android.synthetic.main.activity_picture.picture_back
import java.util.Date


class PictureActivity : AppCompatActivity() {
    private lateinit var adapter: PictureAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)

        recyclerView = findViewById(R.id.orange_rv)
        val dataList = createDataList() // 创建数据列表
        adapter = PictureAdapter(dataList) // 创建适配器
        recyclerView.adapter = adapter // 设置适配器
        // 设置布局管理器，这里使用 LinearLayoutManager
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        picture_back.setOnClickListener {
            finish()
        }
    }
    private fun createDataList(): List<DailyResponse.CustomWeather> {
        val customWeatherList = mutableListOf<DailyResponse.CustomWeather>()
        // 创建顶部数据
        val topTemperature = DailyResponse.Temperature(
            max = 25.0f,
            min = 15.0f
        )
        val topSkycon = DailyResponse.Skycon(
            value = "CLEAR_DAY",
            date = Date(2024 - 1900, 3, 22)
        )
        val topData = DailyResponse.CustomWeather(
            temperature = topTemperature,
            skycon = topSkycon,
            type = DailyResponse.TYPE_TOP
        )
        customWeatherList.add(topData)
        // 创建中间数据
        val centerTemperature = DailyResponse.Temperature(
            max = 28.0f,
            min = 18.0f
        )
        val centerSkycon = DailyResponse.Skycon(
            value = "PARTLY_CLOUDY_DAY",
            date = Date(2024 - 1900, 3, 19)
        )
        val centerData = DailyResponse.CustomWeather(
            temperature = centerTemperature,
            skycon = centerSkycon,
            type = DailyResponse.TYPE_CENTER
        )
        customWeatherList.add(centerData)
        // 创建底部数据
        val bottomTemperature = DailyResponse.Temperature(
            max = 30.0f,
            min = 20.0f
        )
        val bottomSkycon = DailyResponse.Skycon(
            value = "RAIN",
            date = Date(2024 - 1900, 3, 20)
        )
        val bottomData = DailyResponse.CustomWeather(
            temperature = bottomTemperature,
            skycon = bottomSkycon,
            type = DailyResponse.TYPE_BOTTOM
        )
        customWeatherList.add(bottomData)
        return customWeatherList
    }
}