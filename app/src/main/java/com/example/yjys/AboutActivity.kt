package com.example.yjys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.activity_about.about_back
import kotlinx.android.synthetic.main.activity_about.about_title
import kotlinx.android.synthetic.main.activity_about.checkVersion_btn
import kotlinx.android.synthetic.main.activity_about.feedback_btn
import kotlinx.android.synthetic.main.activity_about.privacy_tv
import kotlinx.android.synthetic.main.activity_about.suppose_btn
import kotlinx.android.synthetic.main.activity_about.user_tv

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        about_title.text = "关于我们"
        user_tv.setOnClickListener {
            val intent = Intent(this, WebActivity::class.java)
            intent.putExtra("url", "https://txt.open-iot.net/useragreement/open-iot.html")
            intent.putExtra("title", "用户协议")
            startActivity(intent)
        }
        privacy_tv.setOnClickListener {
            val intent = Intent(this, WebActivity::class.java)
            intent.putExtra("url", "https://txt.open-iot.net/com.x.istar.html")
            intent.putExtra("title", "隐私政策") // 添加此行以传递标题
            startActivity(intent)
        }
        suppose_btn.setOnClickListener {
            Toast.makeText(this,"感谢支持",Toast.LENGTH_SHORT).show()
        }
        feedback_btn.setOnClickListener {
            Toast.makeText(this,"开发中",Toast.LENGTH_SHORT).show()
        }
        checkVersion_btn.setOnClickListener {
            Toast.makeText(this,"已是最新版本",Toast.LENGTH_SHORT).show()
        }
        about_back.setOnClickListener {
            finish()
        }
    }
}