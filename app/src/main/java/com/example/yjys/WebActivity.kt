package com.example.yjys

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web.web_back
import kotlinx.android.synthetic.main.activity_web.web_title

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        val webView : WebView = findViewById(R.id.web_view)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = android.webkit.WebViewClient()
        val url = intent.getStringExtra("url")
        val title = intent.getStringExtra("title")
        title?.let {
            web_title.setText(title) // 设置标题

            web_back.setOnClickListener {
                finish() // 当 imgback 被点击时结束活动
            }
        }
        url?.let {
            webView.loadUrl(it)
        }
    }
}