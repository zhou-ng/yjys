package com.example.yjys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.yjys.constant.PRIVACY_POLICY_URL
import com.example.yjys.constant.USER_AGREE_URL
import kotlinx.android.synthetic.main.activity_about.about_back
import kotlinx.android.synthetic.main.activity_about.about_title
import kotlinx.android.synthetic.main.activity_about.checkVersion_btn
import kotlinx.android.synthetic.main.activity_about.feedback_btn
import kotlinx.android.synthetic.main.activity_about.privacy_tv
import kotlinx.android.synthetic.main.activity_about.suppose_btn
import kotlinx.android.synthetic.main.activity_about.user_tv

class AboutActivity : AppCompatActivity() {

    companion object {
        const val INTENT_URL = "INTENT_URL"
        const val INTENT_TITLE = "INTENT_TITLE"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        about_title.text = getString(R.string.about_us)
        user_tv.setOnClickListener {
            val intent = Intent(this, WebActivity::class.java)
            intent.putExtra(INTENT_URL, USER_AGREE_URL)
            intent.putExtra(INTENT_TITLE, getString(R.string.user_agreement))
            startActivity(intent)
        }
        privacy_tv.setOnClickListener {
            val intent = Intent(this, WebActivity::class.java)
            intent.putExtra(INTENT_URL, PRIVACY_POLICY_URL)
            intent.putExtra(INTENT_TITLE, getString(R.string.privacy_policy)) // 添加此行以传递标题
            startActivity(intent)
        }
        suppose_btn.setOnClickListener {
            Toast.makeText(this,getString(R.string.thank_suppose),Toast.LENGTH_SHORT).show()
        }
        feedback_btn.setOnClickListener {
            Toast.makeText(this, getString(R.string.developing),Toast.LENGTH_SHORT).show()
        }
        checkVersion_btn.setOnClickListener {
            Toast.makeText(this, getString(R.string.already_new_version),Toast.LENGTH_SHORT).show()
        }
        about_back.setOnClickListener {
            finish()
        }
    }
}