package com.kt.rxdemo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import java.util.concurrent.TimeUnit
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.reactivestreams.Subscription

@SuppressLint("CheckResult")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger.addLogAdapter(AndroidLogAdapter())

//        init()              //初始化的操作
//        threadChange()      //线程切换的操作

        mTv.setOnClickListener {
            startActivity(Intent(this, HandlerActivity::class.java))
        }

    }


    /**
     * 创建观察者和被观察者
     * 分别对应着RxJava中的Observable和Observer，它们之间的连接就对应着subscribe()
     * 只有当Observable(上游)和Observer(下游)建立连接之后, 上游才会开始发送事件. 也就是调用了subscribe() 方法之后才开始发送事件
     * 打印结果为：
     * onSubscribe
     * onNext  --->  1
     * onNext  --->  2
     * onNext  --->  3
     * onComplete
     *
     * ObservableEmitter解释：
     * Emitter就是发射器的，这个就是用来发出事件的，它可以发出三种类型的事件，通过调用emitter的onNext(T value)、onComplete()和onError(Throwable error)就可以分别发出next事件、complete事件和error事件。
     * 这里需要注意的是：
     * 1、上游可以发送无限个onNext, 下游也可以接收无限个onNext。当上游发送了一个onComplete后, 上游onComplete之后的事件将会继续发送, 而下游收到onComplete事件之后将不再继续接收事件。
     * 2、当上游发送了一个onError后, 上游onError之后的事件将继续发送, 而下游收到onError事件之后将不再继续接收事件。上游可以不发送onComplete或onError。
     * 3、发送多个onComplete是可以正常运行的, 但是收到第一个onComplete就不再接收了, 但若是发送多个onError, 则收到第二个onError事件会导致程序会崩溃。
     *
     * Disposable解释：
     * 这个单词的意思：是一次性的,用后就抛弃的. 那么在RxJava中怎么去理解它呢, 对应于上面的例子, 我们可以把它理解成水闸, 当调用它的dispose()方法时, 水闸关闭, 下游就收不到事件。
     * 但是调用dispose()并不会让上游停止继续发送事件, 相反上游会继续发送剩余的事件.
     *
     */
    private fun init() {
        //创建Observable（被观察者）
        val observable = Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(emitter: ObservableEmitter<String>) {
                emitter.onNext("1")
                emitter.onNext("2")
                emitter.onNext("3")
                emitter.onComplete()
            }
        })

        //创建Observer（观察者）
        val observer = (object : Observer<String> {
            override fun onComplete() {
                Logger.d("onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                Logger.d("onSubscribe")
            }

            override fun onNext(t: String) {
                Logger.d("onNext  --->  $t")
            }

            override fun onError(e: Throwable) {
                Logger.d("onError")
            }

        })

        //建立订阅关系
        observable.subscribe(observer)

        /** 以下为链式调用 */
        Observable.create(object : ObservableOnSubscribe<Int> {
            override fun subscribe(emitter: ObservableEmitter<Int>) {
                emitter.onNext(1)
                emitter.onNext(2)
                emitter.onNext(3)
                emitter.onComplete()
            }
        }).subscribe(object : Observer<Int> {
            override fun onComplete() {
                Logger.d("onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                Logger.d("onSubscribe")
            }

            override fun onNext(t: Int) {
                Logger.d("onNext  --->  $t")
            }

            override fun onError(e: Throwable) {
                Logger.d("onError")
            }
        })

    }

    /**
     * subscribeOn() 指定发送事件的线程, observeOn() 指定接收事件的线程.
     * Schedulers.io() ：代表io操作的线程, 通常用于网络,读写文件等io密集型的操作
     * Schedulers.computation() ：代表CPU计算密集型的操作, 例如需要大量计算的操作
     * Schedulers.newThread() ：代表一个常规的新线程
     * AndroidSchedulers.mainThread() ：代表Android的主线程
     */
    private fun threadChange() {
        Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(emitter: ObservableEmitter<String>) {
                emitter.onNext("子线程处理，主线程接收！！")
            }
        }).subscribeOn(Schedulers.io())                     //指定处理事件的线层为IO
            .observeOn(AndroidSchedulers.mainThread())      //指定接收尸检结果的线程为main
            .subscribe(object : Observer<String> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: String) {
                    Logger.d("onNext    +   $t  +   ${Thread.currentThread().name}")
                    //onNext    +   子线程处理，主线程接收！！  +   main
                }

                override fun onError(e: Throwable) {

                }

            })
    }

}
