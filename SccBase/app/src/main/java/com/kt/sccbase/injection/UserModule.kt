package com.kt.sccbase.injection

import com.kt.sccbase.BaseService
import com.kt.sccbase.ServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Administrator on 2018-03-26.
 * Module是相当于一个简单的工厂的..
 * 先给UserService来实现依赖注入
 */
@Module //Module的工厂类用@Module来注解
class UserModule {
    @Provides   //实例化UserService的方法
    fun providesUserService(service: ServiceImpl): BaseService {
        return service
    }
}