package com.kt.audiolib.app

import android.app.Application

/*
 * @author : created by CC
 * time    : 2019/10/17  13:45
 * desc    : 
 */

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        
        //初始化AudioHelper
        AudioHelper.init(this)
    }

}