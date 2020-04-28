package com.kt.crashdemo

import android.app.Application
import android.widget.Toast
import android.os.Looper
import android.os.Handler
import android.util.Log


class CrashApp : Application() {

    val TAG = "Scc"


//    override fun attachBaseContext(base: Context?) {
//        super.attachBaseContext(base)
//
//        Handler(Looper.getMainLooper()).post {
//            while (true) {
//                try {
//                    Looper.loop()
//                } catch (e: Throwable) {
//                    Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
//                }
//            }
//        }
//
//        Thread.setDefaultUncaughtExceptionHandler { t, e ->
//            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
//        }
//    }

    override fun onCreate() {
        super.onCreate()


    }
}