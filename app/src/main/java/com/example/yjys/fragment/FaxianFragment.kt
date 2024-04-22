package com.example.yjys.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.yjys.PictureActivity
import com.example.yjys.R
import com.example.yjys.WeatherMainActivity
import com.example.yjys.adapter.FaXianGridViewAdapter
import com.example.yjys.entity.FaXian
import com.example.yjys.faxianui.MusicActivity

class FaxianFragment() : Fragment() {

    var inflate : View? = null
    val data = arrayListOf<FaXian>(FaXian("全国天气",R.drawable.tianqi),FaXian("在线音乐",
        R.drawable.music), FaXian("彩云天气",R.drawable.weather),FaXian("图片展示",R.drawable.picture))

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(inflate == null){
            inflate = inflater.inflate(R.layout.faxian_fragment, container, false)
        }
        val title = inflate?.findViewById<TextView>(R.id.comTitle)
        title?.setText("发现")
        val gride = inflate?.findViewById<GridView>(R.id.gride)
        gride?.adapter=FaXianGridViewAdapter(requireActivity(),data)
        gride?.setOnItemClickListener { parent, view, position, id ->
            when(position){
                0 -> {
                    Toast.makeText(context, "开发中...", Toast.LENGTH_SHORT).show()
                }
                1 -> {
                    startActivity(Intent(context,MusicActivity::class.java))
                }
                2 -> {
//                    Toast.makeText(context, "学习中...", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(context,WeatherMainActivity::class.java))
                }
                3 -> {
                    startActivity(Intent(context,PictureActivity::class.java))
                }
            }
        }

        return inflate
    }
}