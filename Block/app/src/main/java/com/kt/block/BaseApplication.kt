package com.kt.block

import android.app.Application
import com.github.moduth.blockcanary.BlockCanary



/*
 * @author : created by CC
 * time    : 2019/10/30  11:43
 * desc    : 
 */

class BaseApplication :Application(){
    override fun onCreate() {
        super.onCreate()
        BlockCanary.install(this, AppBlockCanaryContext()).start()
    }
}