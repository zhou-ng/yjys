package com.example.yjys.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yjys.Play
import com.example.yjys.R
import com.example.yjys.entity.Favorite

class FavoritesAdapter(val context: Context, data: List<Favorite>) :
    RecyclerView.Adapter<FavoritesAdapter.MyHolder>() {
    private var isAllSelected = false
    val dataList=data

    inner class MyHolder(view: View) : RecyclerView.ViewHolder(view) {
         var title : TextView = view.findViewById(R.id.stitle)
        var id : TextView = view.findViewById(R.id.tk)
        var img : ImageView = view.findViewById(R.id.img)
        var item : LinearLayout = view.findViewById(R.id.item)
        var ck : CheckBox = view.findViewById(R.id.ck)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var inflate:View = LayoutInflater.from(context)
                .inflate(R.layout.favorites_items, parent, false)
        return MyHolder(inflate)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val favorite = dataList[position]
        holder.ck.isChecked = favorite.checkSelect
        holder.title.setText(dataList.get(position).title)
        holder.id.setText(dataList.get(position).id.toString())
        Glide.with(context).load(dataList.get(position).img).into(holder.img)
        holder.item.setOnClickListener {
            val intent = Intent(context, Play::class.java)
            intent.putExtra("url",dataList.get(position).url)
            intent.putExtra("title",dataList.get(position).title)
            intent.putExtra("img",dataList.get(position).img)
            context.startActivity(intent)

        }
        holder.ck.setOnClickListener{
            dataList[position].checkSelect = holder.ck.isChecked
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun selectAllItems() {
        isAllSelected = !isAllSelected
        for (favorite in dataList) {
            favorite.checkSelect = isAllSelected
        }
        notifyDataSetChanged()
    }

    fun isAllItemsSelected(): Boolean {
        for (favorite in dataList) {
            if (!favorite.checkSelect) {
                return false
            }
        }
        return true
    }

}