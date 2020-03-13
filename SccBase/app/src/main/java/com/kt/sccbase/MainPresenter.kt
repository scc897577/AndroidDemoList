package com.kt.sccbase

import com.kotlin.base.rx.BaseSubscriber
import com.kt.lib_base.ext.execute
import com.kt.lib_base.presenter.BasePresenter
import javax.inject.Inject

class MainPresenter @Inject constructor() : BasePresenter<MainView>() {

    @Inject
    lateinit var service: BaseService

    fun test(appKey: String, type: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        service.test(appKey, type).execute(object : BaseSubscriber<TestBean>(mView) {
            override fun onNext(t: TestBean) {
                mView.onTest(t)
            }
        }, lifecycleProvider)
    }
}