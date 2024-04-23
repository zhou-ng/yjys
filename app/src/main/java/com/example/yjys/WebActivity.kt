package com.example.yjys

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web.web_back
import kotlinx.android.synthetic.main.activity_web.web_title

class WebActivity : AppCompatActivity() {
    companion object{
        const val INTENT_URL = "url"
        const val INTENT_TITLE = "title"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        val webView : WebView = findViewById(R.id.web_view)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = android.webkit.WebViewClient()
        val url = intent.getStringExtra(INTENT_URL)
        val title = intent.getStringExtra(INTENT_TITLE)
        title?.let {
            web_title.setText(title) // 设置标题

            web_back.setOnClickListener {
                finish()
            }
        }
        url?.let {
            webView.loadUrl(it)
        }
    }
}