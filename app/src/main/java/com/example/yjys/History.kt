package com.example.yjys

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yjys.adapter.FavoritesAdapter
import com.example.yjys.entity.Favorite
import com.example.yjys.utils.MyDb
import kotlinx.android.synthetic.main.activity_favorites.*
import kotlinx.android.synthetic.main.common_title.*

class History : AppCompatActivity() {

    var myDb : SQLiteDatabase? = null
    var list = mutableListOf<Favorite>()
    var favoritesAdapter : FavoritesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        supportActionBar?.hide()

        val db = MyDb(this, "myData.db", 1)
        myDb = db.writableDatabase

        val linearLayoutManager = LinearLayoutManager(this)
        rev.layoutManager = linearLayoutManager

        favoritesAdapter = FavoritesAdapter(this, list)
        rev.adapter = favoritesAdapter
        ref()
        quan.setOnClickListener {
           favoritesAdapter?.selectAllItems()
        }

        del.setOnClickListener {
            delSelectedItems()
        }

        comTitle.text="播放历史"
        imgback.setOnClickListener{
            finish()
        }

    }

    override fun onRestart() {
        super.onRestart()
        ref()
    }

    fun ref(){
        list.clear()
        val rawQuery = myDb?.rawQuery("select * from history", null)
        if (rawQuery?.moveToFirst()!!){
            while (rawQuery.moveToNext()){
                val id = rawQuery.getInt(rawQuery.getColumnIndex("id"))
                val title =rawQuery.getString(rawQuery.getColumnIndex("title"))
                val img =rawQuery.getString(rawQuery.getColumnIndex("img"))
                val url =rawQuery.getString(rawQuery.getColumnIndex("url"))

                list.add(Favorite(id, title, img, url))
            }

        }
        favoritesAdapter?.notifyDataSetChanged()
    }

    private fun delSelectedItems(){
        val selectedItems = mutableListOf<Favorite>()
        for (favorite in  list){
            if (favorite in list){
                selectedItems.add(favorite)
            }
        }
        if (selectedItems.isEmpty()){
            Toast.makeText(this,"未选择任何选项",Toast.LENGTH_SHORT).show()
            return
        }
        for (selectedItem in selectedItems){
            myDb?.execSQL("DELETE FROM history WHERE id = ?", arrayOf(selectedItem.id))
            list.remove(selectedItem)
        }
        favoritesAdapter?.notifyDataSetChanged()
    }
}