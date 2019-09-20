package com.scc.daggertest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.scc.daggertest.daggerclass.ClassActivity
import com.scc.daggertest.daggerinterface.SccActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mBtnClass.setOnClickListener {
            startActivity(Intent(this, ClassActivity::class.java))
        }

        mBtnInterface.setOnClickListener {
            startActivity(Intent(this, SccActivity::class.java))
        }


    }
}
