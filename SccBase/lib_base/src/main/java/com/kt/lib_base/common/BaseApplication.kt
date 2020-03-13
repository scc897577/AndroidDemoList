package com.kt.lib_base.common

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.kt.lib_base.injection.component.AppComponent
import com.kt.lib_base.injection.component.DaggerAppComponent
import com.kt.lib_base.injection.module.AppModule

/** Application 基类 */
open class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    /** 全局伴生对象 */
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()

        initAppInjection()

        context = this
    }

    /** Application Component初始化 */
    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }


}
