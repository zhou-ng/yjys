package com.example.yjys.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class MyDb(val context: Context,name: String ,version: Int) : SQLiteOpenHelper(context,name,null,version) {
    private val tableSql = "create table history (id integer primary key autoincrement," +
            "title text," +
            "img text," +
            "url text," +
            "cattype text)"

    private val tableFavoritesSql = "create table favorites (id integer primary key autoincrement," +
            "title text," +
            "img text," +
            "url text," +
            "cattype text)"

    private val likesql = "create table likes (id integer primary key autoincrement," +
            "title text," +
            "img text," +
            "url text," +
            "cattype text)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(tableSql)
        db?.execSQL(tableFavoritesSql)
        db?.execSQL(likesql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists history")
        db?.execSQL("drop table if exists favorites")
        db?.execSQL("drop table if exists likes")
        onCreate(db)
    }

}