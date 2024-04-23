package com.example.yjys.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.yjys.R
import com.example.yjys.WeatherActivity
import com.example.yjys.entity.Favorite

import com.example.yjys.fragment.PlaceFragment
import com.example.yjys.model.Place
import kotlinx.android.synthetic.main.activity_weather.drawerLayout

class PlaceAdapter(private val fragment: PlaceFragment) :
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {
    companion object {
        const val LOCATION_LNG = "location_lng"
        const val LOCATION_LAT = "location_lat"
        const val PLACE_NAME = "place_name"
    }
    var dataList = mutableListOf<Place>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val placeName: TextView = view.findViewById(R.id.placeName)
        val placeAddress: TextView = view.findViewById(R.id.placeAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.place_item,
            parent, false
        )
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val place = dataList[position]
            val activity = fragment.activity
            if (activity is WeatherActivity) {
                activity.drawerLayout.closeDrawers()
                activity.viewModel.locationLng = place.location.lng
                activity.viewModel.locationLat = place.location.lat
                activity.viewModel.placeName = place.name
                activity.refreshWeather()
            } else {
                val intent = Intent(parent.context, WeatherActivity::class.java).apply {
                    putExtra(LOCATION_LNG, place.location.lng)
                    putExtra(LOCATION_LAT, place.location.lat)
                    putExtra(PLACE_NAME, place.name)
                }
                fragment.startActivity(intent)
                activity?.finish()
            }
            fragment.viewModel.savePlace(place)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = dataList[position]
        holder.placeName.text = place.name
        holder.placeAddress.text = place.address
    }

    override fun getItemCount() = dataList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(placeList: MutableList<Place>) {
        dataList.clear()
        dataList.addAll(placeList)
        notifyDataSetChanged()
    }
}