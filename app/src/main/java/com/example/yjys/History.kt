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

class History : AppCompatActivity() {

    private var myDb: SQLiteDatabase? = null
    var list = mutableListOf<Favorite>()
    private var favoritesAdapter: FavoritesAdapter? = null

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
        selectAll.setOnClickListener {
            favoritesAdapter?.selectAllItems()
            updateSelectAllButton()
        }

        del.setOnClickListener {
            delSelectedItems()
        }

        comTitle.text = getString(R.string.play_history)
        img_back.setOnClickListener {
            finish()
        }

    }

    override fun onRestart() {
        super.onRestart()
        ref()
    }

    private fun ref() {
        val temp = mutableListOf<Favorite>()
        val rawQuery = myDb?.rawQuery("select * from history", null)
        while (rawQuery?.moveToNext() == true) {
            val id = rawQuery.getInt(rawQuery.getColumnIndex("id"))
            val title = rawQuery.getString(rawQuery.getColumnIndex("title"))
            val img = rawQuery.getString(rawQuery.getColumnIndex("img"))
            val url = rawQuery.getString(rawQuery.getColumnIndex("url"))
            temp.add(Favorite(id, title, img, url))
        }

        favoritesAdapter?.resetData(temp)
    }

    private fun updateSelectAllButton() {
        val isAllSelected = favoritesAdapter?.isAllItemsSelected() ?: false
        if (isAllSelected) {
            selectAll.text = getString(R.string.no_select_all)
        } else {
            selectAll.text = getString(R.string.select_all)
        }
    }

    private fun delSelectedItems() {
        val selectedItems = mutableListOf<Favorite>()
        for (favorite in list) {
            if (favorite.checkSelect) { // 只添加被选中的项
                selectedItems.add(favorite)
            }
        }
        if (selectedItems.isEmpty()) {
            Toast.makeText(this, getString(R.string.no_select_item), Toast.LENGTH_SHORT).show()
            return
        }
        for (selectedItem in selectedItems) {
            myDb?.execSQL("DELETE FROM history WHERE id = ?", arrayOf(selectedItem.id))
            list.remove(selectedItem)
        }
        favoritesAdapter?.notifyDataSetChanged()
        updateSelectAllButton()
    }
}