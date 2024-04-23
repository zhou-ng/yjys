package com.example.yjys

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yjys.adapter.FavoritesAdapter
import com.example.yjys.entity.Favorite
import com.example.yjys.utils.MyDb
import kotlinx.android.synthetic.main.activity_favorites.del
import kotlinx.android.synthetic.main.activity_favorites.selectAll
import kotlinx.android.synthetic.main.activity_favorites.rev
import kotlinx.android.synthetic.main.common_title.comTitle
import kotlinx.android.synthetic.main.common_title.img_back

class LikeActivity : AppCompatActivity() {
    private var myDb: SQLiteDatabase? = null
    private var list = mutableListOf<Favorite>()
    private var favoritesAdapter: FavoritesAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_like)
        supportActionBar?.hide()
        val db = MyDb(this, "myData.db", 1)
        myDb = db.writableDatabase

        val linearLayoutManager = LinearLayoutManager(this)
        rev.layoutManager = linearLayoutManager
        favoritesAdapter = FavoritesAdapter(this, list)
        rev.adapter = favoritesAdapter
        ref()

        comTitle.text = getString(R.string.my_like)  // 设置页面标题
        //  页面返回
        img_back.setOnClickListener {
            finish()
        }
        //  全选
        selectAll.setOnClickListener {
            favoritesAdapter?.selectAllItems()
            updateSelectAllButton()
        }
        // 删除
        del.setOnClickListener {
            delSelectedItems()
        }
    }

    override fun onRestart() {
        super.onRestart()
        ref()
    }

    private fun ref() {
        val temp = mutableListOf<Favorite>()
        val rawQuery = myDb?.rawQuery("SELECT * FROM likes", null)
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
        favoritesAdapter?.let {
            val selectedItems = mutableListOf<Favorite>()
            for (favorite in it.dataList) {
                if (favorite.checkSelect) {
                    selectedItems.add(favorite)
                }
            }
            if (selectedItems.isEmpty()) {
                Toast.makeText(this, getString(R.string.no_select_item), Toast.LENGTH_SHORT).show()
                return
            }
            for (selectedItem in selectedItems) {
                myDb?.execSQL("DELETE FROM likes WHERE id = ?", arrayOf(selectedItem.id))

            }
            favoritesAdapter?.deleteData(selectedItems)
            updateSelectAllButton()
        }
    }
}