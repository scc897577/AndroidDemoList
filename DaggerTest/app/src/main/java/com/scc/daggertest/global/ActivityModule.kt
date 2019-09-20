package com.scc.daggertest.global

import android.app.Activity
import dagger.Module
import dagger.Provides

/**
 *  author: scc
 *  date: 2019/7/9   11:13
 *  desc:
 */
@Module
class ActivityModule(private val activity: Activity) {
    @Provides
    fun provideActivity(): Activity {
        return activity
    }
}