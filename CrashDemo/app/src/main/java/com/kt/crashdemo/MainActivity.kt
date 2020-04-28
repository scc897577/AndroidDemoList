package com.kt.crashdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        mtv.setOnClickListener {
//            startActivity(Intent(this,SccActivity::class.java))
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}
