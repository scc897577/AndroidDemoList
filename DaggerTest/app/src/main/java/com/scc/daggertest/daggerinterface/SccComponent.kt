package com.scc.daggertest.daggerinterface

import dagger.Component

/**
 *  author: scc
 *  date: 2019/7/9   9:52
 *  desc: Component还是以桥梁和管理者的身份进行连接，将SccModule中返回的SccService的实现类的对象SccServiceImpl和
 *  要引用它的activity-SccActivity连接起来
 */
@Component(modules = [(SccModule::class)])
interface SccComponent {
    fun inject(activity: SccActivity)
}