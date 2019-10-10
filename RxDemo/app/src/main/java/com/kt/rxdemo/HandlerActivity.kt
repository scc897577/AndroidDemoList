package com.kt.rxdemo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_handler.*
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
class HandlerActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)

        mBtn.setOnClickListener {
            //从0到99，延迟0秒后，隔一秒发送一次
            Observable.intervalRange(0, 100, 0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    Logger.d("当前线程  --> ${Thread.currentThread().name}  进度值为  --->  $it")
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Long> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: Long) {
                        mTv.text = t.toString()
                        Logger.d("当前线程  --> ${Thread.currentThread().name}  进度值为  --->  $t")
                    }

                    override fun onError(e: Throwable) {
                    }
                })
        }
    }
}
