package com.kt.lib_base.injection.module

import android.app.Activity
import com.kt.lib_base.injection.ActivityScope
import dagger.Module
import dagger.Provides

/*
    Activity级别Module
 */
@Module
class ActivityModule(private val activity: Activity) {

    @ActivityScope
    @Provides
    fun provideActivity(): Activity {
        return this.activity
    }
}
