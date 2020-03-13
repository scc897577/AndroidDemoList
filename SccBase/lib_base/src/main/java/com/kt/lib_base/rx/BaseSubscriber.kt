package com.kt.lib_base.rx

import com.kotlin.base.rx.DataNullException
import com.kt.lib_base.presenter.view.BaseView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/*
    Rx订阅者默认实现
 */
open class BaseSubscriber<T>(val baseView: BaseView) : Observer<T> {

    override fun onSubscribe(p0: Disposable) {
    }


    override fun onComplete() {
        baseView.hideLoading()
    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
        baseView.hideLoading()
        if (e is BaseException) {
            baseView.onError(e.msg)
        } else if (e is DataNullException){
            baseView.onDataIsNull()
        }

    }
}
