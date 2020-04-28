package com.kt.huyatab

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

/**
 * 有Flyco tablelayout  自己在实现吧
 *
 */
class MainActivity : AppCompatActivity(), QRListener {

    private var mReceiver: QRReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    private fun initReceiver() {
        mReceiver = QRReceiver()
        mReceiver!!.setListener(this)
        val filter = IntentFilter()
        filter.addAction("com.scanner.broadcast")
        registerReceiver(mReceiver, filter)
    }


    override fun onResume() {
        super.onResume()
        initReceiver()
    }

    override fun onPause() {
        super.onPause()
        if (mReceiver != null) {
            unregisterReceiver(mReceiver)
            mReceiver = null
            System.gc()
        }
    }

    override fun getQRData(data: String) {
        Log.e("sccc", data)
    }

}
