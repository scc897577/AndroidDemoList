package com.kt.crashdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loveplusplus.update.UpdateChecker
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        UpdateChecker.checkForDialog(this)

        mtv.setOnClickListener {
            //            startActivity(Intent(this,SccActivity::class.java))
            throw Throwable("这是一个异常")
        }

    }
}
