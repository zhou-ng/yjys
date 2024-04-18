package com.example.yjys.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.yjys.R
import com.example.yjys.model.DailyResponse
import com.example.yjys.model.Weather
import com.example.yjys.model.getSky
import com.example.yjys.viewmodel.WeatherViewModel
import java.lang.IllegalArgumentException
import java.text.SimpleDateFormat
import java.util.Locale


class OrangeAdapter(private val dataList: List<DailyResponse.CustomWeather>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class HorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateInfo: TextView  = itemView.findViewById(R.id.hor_dateInfo)
        val skyIcon: ImageView = itemView.findViewById(R.id.hor_skyIcon)
        val skyInfo: TextView = itemView.findViewById(R.id.hor_skyInfo)
        val temperatureInfo: TextView = itemView.findViewById(R.id.hor_temperatureInfo)
    }

    inner class VtlViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateInfo: TextView  = itemView.findViewById(R.id.vtl_dateInfo)
        val skyIcon: ImageView = itemView.findViewById(R.id.vtl_skyIcon)
        val skyInfo: TextView = itemView.findViewById(R.id.vtl_skyInfo)
        val temperatureInfo: TextView = itemView.findViewById(R.id.vtl_temperatureInfo)
    }

    override fun getItemViewType(position: Int): Int {
        val data = dataList[position]
        return data.type
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = if (viewType == DailyResponse.TYPE_HOR){
        val view = LayoutInflater.from(parent.context).inflate(R.layout.forecast_item,parent,false)
        HorViewHolder(view)
    } else {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.forecast_item_vertical,parent,false)
        VtlViewHolder(view)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = dataList[position]
        when (holder) {
            is HorViewHolder -> {
                val skycon = data.skycon
                val temperature = data.temperature

                val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                holder.dateInfo.text = simpleDateFormat.format(skycon.date)

                val sky = getSky(skycon.value)
                holder.skyIcon.setImageResource(sky.icon)
                holder.skyInfo.text = sky.info

                val tempText = "${temperature.min.toInt()} ~ ${temperature.max.toInt()} ℃"
                holder.temperatureInfo.text = tempText
            }
            is VtlViewHolder -> {
                val skycon = data.skycon
                val temperature = data.temperature
                val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                holder.dateInfo.text = simpleDateFormat.format(skycon.date)
                val sky = getSky(skycon.value)
                holder.skyIcon.setImageResource(sky.icon)
                holder.skyInfo.text = sky.info
                val tempText = "${temperature.min.toInt()} ~ ${temperature.max.toInt()} ℃"
                holder.temperatureInfo.text = tempText
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}