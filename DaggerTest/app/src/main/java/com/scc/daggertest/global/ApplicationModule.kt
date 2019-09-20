package com.scc.daggertest.global

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 *  author: scc
 *  date: 2019/7/9   11:10
 *  desc: Application的Module
 */
@Module
class ApplicationModule(private val context: BaseApplication) {

    @Provides
    fun provideContext(): Context {
        return context
    }
}