package com.kt.rxdemo.operator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kt.rxdemo.R
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import io.reactivex.Observer
import java.util.concurrent.TimeUnit

/**
 * 当到指定时间后就会发送一个 0 的值给观察者。 在项目中，可以做一些延时的处理，类似于Handler中的延时
 */
class TimerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        init()

    }

    @SuppressLint("CheckResult")
    private fun init() {
        Observable.timer(3, TimeUnit.SECONDS).subscribe {
            Logger.d(it)
            //2019-10-09 16:03:49.004 3191-3435/com.kt.rxdemo D/PRETTY_LOGGER: │ 0
        }
    }
}
