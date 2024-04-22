package com.example.yjys

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yjys.adapter.FavoritesAdapter
import com.example.yjys.entity.Favorite
import com.example.yjys.utils.MyDb
import kotlinx.android.synthetic.main.activity_favorites.*
import kotlinx.android.synthetic.main.common_title.*

class Favorites : AppCompatActivity() {

    var myDb : SQLiteDatabase? = null
    var list = mutableListOf<Favorite>()
    var favoritesAdapter : FavoritesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        supportActionBar?.hide()
        val db = MyDb(this, "myData.db", 1)
        myDb = db.writableDatabase

        val linearLayoutManager = LinearLayoutManager(this)
        rev.layoutManager=linearLayoutManager
        favoritesAdapter = FavoritesAdapter(this, list)
        rev.adapter = favoritesAdapter
        ref()

        selectAll.setOnClickListener {
            favoritesAdapter?.selectAllItems()
            updateSelectAllButton()

        }

        del.setOnClickListener {
            delSelectedItems()
        }

        comTitle.text="我的收藏"
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
        val rawQuery = myDb?.rawQuery("select * from favorites", null)
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

    private fun updateSelectAllButton() {
        val isAllSelected = favoritesAdapter?.isAllItemsSelected() ?: false
        if (isAllSelected) {
            selectAll.text = "全不选"
        } else {
            selectAll.text = "全选"
        }
    }
    private fun delSelectedItems() {
        val selectedItems = mutableListOf<Favorite>()
        for (favorite in list) {
            if (favorite.checkSelect) {
                selectedItems.add(favorite)
            }
        }
        if (selectedItems.isEmpty()){
            Toast.makeText(this,"您未选择任何选项", Toast.LENGTH_SHORT).show()
            return
        }
        for (selectedItem in selectedItems) {
            myDb?.execSQL("DELETE FROM favorites WHERE id = ?", arrayOf(selectedItem.id))
            list.remove(selectedItem)
        }
        favoritesAdapter?.notifyDataSetChanged()
        updateSelectAllButton()

    }

}