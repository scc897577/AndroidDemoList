package com.kt.huyatab

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class QRReceiver: BroadcastReceiver()  {
    private var listener: QRListener? = null

    fun setListener(listener: QRListener) {
        this.listener = listener
    }

    override fun onReceive(context: Context?, intent: Intent) {
        val action = intent.action
        if (action != null && action == "com.scanner.broadcast") {
            val data = intent.getStringExtra("data")
            if (listener != null) {
                listener!!.getQRData(data)
            }
        }

    }

}