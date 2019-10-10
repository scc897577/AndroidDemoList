package com.kt.rxdemo.operator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kt.rxdemo.R
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import java.lang.Exception
import java.lang.IllegalArgumentException

/**
 * 这个方法可以将事件序列中的元素进行整合加工，返回一个新的被观察者。
 * flatMap() 其实与 map() 类似，但是 flatMap() 返回的是一个 Observerable，map()只是返回数据，
 * 如果在元素再加工的时候，想再使用上面的创建操作符的话，建议使用flatMap()，而非map()。
 */
class FlatMapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flat_map)

        init()
    }

    @SuppressLint("CheckResult")
    private fun init() {
//        Observable.just("飞机", "大炮", "轮船","日本").flatMap{
//            if (it == "日本") {
////                return@flatMap Observable.error { Exception("日本不能被喜欢") }
//                return@flatMap Observable.error( Exception("贪官不能被喜欢"));
//            }
//            return@flatMap Observable.just("我爱$it")
//        }

        Observable.just("飞机", "大炮", "轮船", "日本").flatMap(object : Function<String, ObservableSource<String>> {
            override fun apply(t: String): ObservableSource<String> {
                if (t == "日本") {
                    return Observable.error(Exception("日本不能被喜欢"))
                }
                return Observable.just("我爱$t")
            }
        }).subscribe({ Logger.d(it) },
            //2019-10-09 16:36:03.529 6372-6372/com.kt.rxdemo D/PRETTY_LOGGER: │ 我爱飞机
            //2019-10-09 16:36:03.530 6372-6372/com.kt.rxdemo D/PRETTY_LOGGER: │ 我爱大炮
            //2019-10-09 16:36:03.530 6372-6372/com.kt.rxdemo D/PRETTY_LOGGER: │ 我爱轮船
            { Logger.d(it.message) }
            //2019-10-09 16:36:03.531 6372-6372/com.kt.rxdemo D/PRETTY_LOGGER: │ 日本不能被喜欢
        )
    }
}
