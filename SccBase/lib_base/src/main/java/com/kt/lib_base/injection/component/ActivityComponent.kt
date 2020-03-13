package com.kt.lib_base.injection.component

import android.app.Activity
import android.content.Context
import com.kt.lib_base.injection.ActivityScope
import com.kt.lib_base.injection.module.ActivityModule
import com.kt.lib_base.injection.module.LifecycleProviderModule
import com.trello.rxlifecycle2.LifecycleProvider
import dagger.Component

/*
    Activity级别Component
 */
@ActivityScope
@Component(dependencies = [(AppComponent::class)],modules = [(ActivityModule::class), (LifecycleProviderModule::class)])
interface ActivityComponent {

    fun activity():Activity
    fun context(): Context
    fun lifecycleProvider(): LifecycleProvider<*>
}
