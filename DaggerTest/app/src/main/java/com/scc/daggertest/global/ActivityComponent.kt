package com.scc.daggertest.global

import android.app.Activity
import android.content.Context
import dagger.Component

/**
 *  author: scc
 *  date: 2019/7/9   11:13
 *  desc:fun inject(obj:目标类)从目标类开始查找@Inject注解生成依赖注入的代码
 *  fun xxx():Obj生成Obj实例供其它组件使用如果Obj本身还包含其它依赖注入也会自动生成对应实例
 */
@Component(modules = [(ActivityModule::class)], dependencies = [(ApplicationComponent::class)])
interface ActivityComponent {
    fun activity(): Activity
    fun context(): Context
}