package com.example.yjys.dialog

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import com.example.yjys.BuildConfig
import com.example.yjys.R
import com.wang.avi.AVLoadingIndicatorView
import java.lang.Exception


class LoadingDiglog(context: Context, id: Int) : AlertDialog(context, id) {

    private var avi: AVLoadingIndicatorView? = null

    companion object {
        @JvmStatic
        private var lodingDiglog: LoadingDiglog? = null
        @JvmStatic
        fun getInstance(context: Context?): LoadingDiglog? {
            lodingDiglog = LoadingDiglog(context!!, R.style.TransparentDialog) //设置AlertDialog背景透明
            lodingDiglog?.setCancelable(false)
            lodingDiglog?.setCanceledOnTouchOutside(false)
            return lodingDiglog
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.loding_dialog)
        avi = findViewById(R.id.loding) as AVLoadingIndicatorView
    }

    override fun show() {
        try {
            super.show()
            avi?.show()
        }catch (e:Exception){
            Log.e("异常",e.toString())
        }

    }


    override fun dismiss() {
        try {
            super.dismiss()
            avi?.hide()
        }catch (e:Exception){
            if (BuildConfig.DEBUG) {
                e.printStackTrace()
            }
        }
    }
}