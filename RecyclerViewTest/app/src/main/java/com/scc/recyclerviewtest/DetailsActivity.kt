package com.scc.recyclerviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*
import org.greenrobot.eventbus.EventBus

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val extra = intent.getIntExtra("position", 0)
        mBtn.setOnClickListener {
            EventBus.getDefault().post(EventBean(mEt.text.toString(), extra))
            finish()
        }
    }
}
