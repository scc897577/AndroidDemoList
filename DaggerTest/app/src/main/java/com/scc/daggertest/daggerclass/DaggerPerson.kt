package com.scc.daggertest.daggerclass

import android.util.Log
import javax.inject.Inject

/**
 *  author: scc
 *  date: 2019/7/8   17:29
 *  desc: dagger中@Inject 构造方法，即代表此类已经实例化成功
 */
class DaggerPerson @Inject constructor() {
    fun printLog(): String {
        return "come on DaggerPerson"
    }
}