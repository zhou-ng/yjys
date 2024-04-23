package com.example.yjys.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yjys.R
import com.example.yjys.constant.PICTURE_URL
import com.example.yjys.model.DailyResponse
import com.example.yjys.model.getSky
import java.lang.IllegalArgumentException
import java.text.SimpleDateFormat
import java.util.Locale


class PictureAdapter(private val dataList: List<DailyResponse.CustomWeather>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val DATE_FORMAT = "yyyy-MM-dd"
    }

    inner class TopViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val topDateInfo: TextView = view.findViewById(R.id.top_dateInfo)
        val topSkyIcon: ImageView = view.findViewById(R.id.top_skyIcon)
        val topSkyInfo: TextView = view.findViewById(R.id.top_skyInfo)
        val topTemperatureInfo: TextView = view.findViewById(R.id.top_temperatureInfo)
        val topIv: ImageView = view.findViewById(R.id.top_iv)
    }

    inner class CenterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val centerDateInfo: TextView = view.findViewById(R.id.center_dateInfo)
        val centerSkyIcon: ImageView = view.findViewById(R.id.center_skyIcon)
        val centerSkyInfo: TextView = view.findViewById(R.id.center_skyInfo)
        val centerTemperatureInfo: TextView = view.findViewById(R.id.center_temperatureInfo)
        val centerLeftIv: ImageView = view.findViewById(R.id.center_left_iv)
        val centerRightIv: ImageView = view.findViewById(R.id.center_right_iv)
    }

    inner class BottomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bottomDateInfo: TextView = view.findViewById(R.id.bottom_dateInfo)
        val bottomSkyIcon: ImageView = view.findViewById(R.id.bottom_skyIcon)
        val bottomSkyInfo: TextView = view.findViewById(R.id.bottom_skyInfo)
        val bottomTemperatureInfo: TextView = view.findViewById(R.id.bottom_temperatureInfo)
        val bottomLeftIv: ImageView = view.findViewById(R.id.bottom_left_iv)
        val bottomCenterIv: ImageView = view.findViewById(R.id.bottom_center_iv)
        val bottomRightIv: ImageView = view.findViewById(R.id.bottom_right_iv)
    }

    override fun getItemViewType(position: Int): Int {
        val data = dataList[position]
        return data.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            DailyResponse.TYPE_TOP -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.picture_topitem, parent, false)
                TopViewHolder(view)
            }

            DailyResponse.TYPE_CENTER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.picture_centeritem, parent, false)
                CenterViewHolder(view)
            }

            DailyResponse.TYPE_BOTTOM -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.picture_bottomitem, parent, false)
                BottomViewHolder(view)
            }

            else -> throw IllegalArgumentException()
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = dataList[position]
        when (holder) {
            is TopViewHolder -> {
                val skyCon = data.skycon
                val temperature = data.temperature
                val simpleDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
                holder.topDateInfo.text = simpleDateFormat.format(skyCon.date)
                val sky = getSky(skyCon.value)
                holder.topSkyIcon.setImageResource(sky.icon)
                holder.topSkyInfo.text = sky.info
                val tempText = holder.itemView.context.getString(R.string.temperature_range)
                    .format(temperature.min.toInt(), temperature.max.toInt())
                holder.topTemperatureInfo.text = tempText
                Glide.with(holder.itemView.context)
                    .load(PICTURE_URL)
                    .placeholder(R.drawable.picture_place)
                    .error(R.drawable.picture_error)
                    .into(holder.topIv)
            }

            is CenterViewHolder -> {
                val skyCon = data.skycon
                val temperature = data.temperature
                val simpleDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
                holder.centerDateInfo.text = simpleDateFormat.format(skyCon.date)
                val sky = getSky(skyCon.value)
                holder.centerSkyIcon.setImageResource(sky.icon)
                holder.centerSkyInfo.text = sky.info
                val tempText = holder.itemView.context.getString(R.string.temperature_range)
                    .format(temperature.min.toInt(), temperature.max.toInt())
                holder.centerTemperatureInfo.text = tempText
                Glide.with(holder.itemView.context)
                    .load(PICTURE_URL)
                    .placeholder(R.drawable.picture_place)
                    .error(R.drawable.picture_error)
                    .into(holder.centerLeftIv)

                Glide.with(holder.itemView.context)
                    .load(PICTURE_URL)
                    .placeholder(R.drawable.picture_place)  // 占位图片
                    .error(R.drawable.picture_error)  // 错误时加载的图片
                    .into(holder.centerRightIv)

            }

            is BottomViewHolder -> {
                val skyCon = data.skycon
                val temperature = data.temperature
                val simpleDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
                holder.bottomDateInfo.text = simpleDateFormat.format(skyCon.date)
                val sky = getSky(skyCon.value)
                holder.bottomSkyIcon.setImageResource(sky.icon)
                holder.bottomSkyInfo.text = sky.info
                val tempText = holder.itemView.context.getString(R.string.temperature_range)
                    .format(temperature.min.toInt(), temperature.max.toInt())
                holder.bottomTemperatureInfo.text = tempText
                Glide.with(holder.itemView.context)
                    .load(PICTURE_URL)
                    .placeholder(R.drawable.picture_place)
                    .error(R.drawable.picture_error)
                    .into(holder.bottomLeftIv)
                Glide.with(holder.itemView.context)
                    .load(PICTURE_URL)
                    .placeholder(R.drawable.picture_place)
                    .error(R.drawable.picture_error)
                    .into(holder.bottomCenterIv)
                Glide.with(holder.itemView.context)
                    .load(PICTURE_URL)
                    .placeholder(R.drawable.picture_place)
                    .error(R.drawable.picture_error)
                    .into(holder.bottomRightIv)
            }

            else -> throw IllegalArgumentException()
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}