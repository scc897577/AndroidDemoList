package com.scc.handwritingeventbus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        mTvSecond.setOnClickListener {
            EventBus.instance.post(EventBean("1", "2"))
        }
    }
}
