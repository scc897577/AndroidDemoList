package com.scc.daggertest.daggerclass

import com.scc.daggertest.MainActivity
import dagger.Component

/**
 *  author: scc
 *  date: 2019/7/8   17:33
 *  desc: Component是一个对象它的自身和引用者对它的引用之间的桥梁
 *  通过自定义inject方法将一个对象和引用者对它的引用连接起来
 */
@Component
interface PersonComponent {
    fun inject(activity: ClassActivity)
}