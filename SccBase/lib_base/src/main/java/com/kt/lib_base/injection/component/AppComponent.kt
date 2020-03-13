package com.kt.lib_base.injection.component

import android.content.Context
import com.kt.lib_base.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

/*
    Application级别Component
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun context():Context
}
