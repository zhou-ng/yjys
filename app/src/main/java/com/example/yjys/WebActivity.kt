package com.example.yjys

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        val webView : WebView = findViewById(R.id.web_view)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = android.webkit.WebViewClient()
        val url = intent.getStringExtra("url")
        url?.let {
            webView.loadUrl(it)
        }
    }
}