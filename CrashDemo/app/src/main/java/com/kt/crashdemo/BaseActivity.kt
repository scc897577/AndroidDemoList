package com.kt.crashdemo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Cockroach.install { thread, throwable ->
            Handler(Looper.getMainLooper()).post(Runnable {
                try {
                    //建议使用下面方式在控制台打印异常，这样就可以在Error级别看到红色log
                    Log.e("AndroidRuntime", "--->CockroachException:$thread<---", throwable)
                    throwable.message?.let {
                        DialogUtilGlobal.buildDialog(this@BaseActivity,"错误",
                            it
                        ).create().show()
                    }
                } catch (e: Throwable) {

                }
            })
        }
    }
}