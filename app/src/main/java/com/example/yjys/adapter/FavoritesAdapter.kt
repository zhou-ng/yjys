package com.example.yjys.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
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

class
FavoritesAdapter(val context: Context, data: MutableList<Favorite>) :
    RecyclerView.Adapter<FavoritesAdapter.MyHolder>() {
    private var isAllSelected = false
    val dataList=data
    companion object {
        const val INTENT_URL = "url"
        const val INTENT_TITLE = "title"
        const val INTENT_IMG = "img"
    }

    inner class MyHolder(view: View) : RecyclerView.ViewHolder(view) {
         var title : TextView = view.findViewById(R.id.moveTitleTV)
        var id : TextView = view.findViewById(R.id.tk)
        var img : ImageView = view.findViewById(R.id.img)
        var item : LinearLayout = view.findViewById(R.id.item)
        var ck : CheckBox = view.findViewById(R.id.ck)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflate:View = LayoutInflater.from(context)
                .inflate(R.layout.favorites_items, parent, false)
        return MyHolder(inflate)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val favorite = dataList[position]
        holder.ck.isChecked = favorite.checkSelect
        holder.title.text = dataList[position].title
        holder.id.text = dataList[position].id.toString()
        Glide.with(context).load(dataList[position].img).into(holder.img)
        holder.item.setOnClickListener {
            val intent = Intent(context, Play::class.java)
            intent.putExtra(INTENT_URL, dataList[position].url)
            intent.putExtra(INTENT_TITLE, dataList[position].title)
            intent.putExtra(INTENT_IMG, dataList[position].img)
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

    @SuppressLint("NotifyDataSetChanged")
    fun resetData(list: MutableList<Favorite>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteData(selectAllItems: MutableList<Favorite>){
        dataList.removeAll(selectAllItems)
        notifyDataSetChanged()
    }
}