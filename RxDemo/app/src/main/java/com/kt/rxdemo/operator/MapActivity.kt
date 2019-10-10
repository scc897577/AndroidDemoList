package com.kt.rxdemo.operator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kt.rxdemo.R
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_map.*

/**
 * map 可以将被观察者发送的数据类型转变成其他的类型
 * 可以对发射过来的数据进行再加工，再传给观察者
 */
class MapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        init()
    }

    @SuppressLint("CheckResult")
    private fun init() {
        Observable.just("飞机", "大炮", "轮船").map {
            return@map "我爱$it"
        }.subscribe {
            Logger.d(it)
            //2019-10-09 16:12:58.795 4358-4358/com.kt.rxdemo D/PRETTY_LOGGER: │ 我爱飞机
            //2019-10-09 16:12:58.795 4358-4358/com.kt.rxdemo D/PRETTY_LOGGER: │ 我爱大炮
            //2019-10-09 16:12:58.795 4358-4358/com.kt.rxdemo D/PRETTY_LOGGER: │ 我爱轮船
        }
    }
}
