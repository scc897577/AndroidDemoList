package com.scc.daggertest.daggerinterface

import dagger.Module
import dagger.Provides

/**
 *  author: scc
 *  date: 2019/7/8   17:42
 *  desc: 将SccService的实现类SccServiceImpl作为参数传递，
 *  发现SccServiceImpl实现类的构造方法已经通过@Inject实例化，所以此时传递进来的参数就是SccServiceImpl的实例化之后的对象
 *  SccModule是工厂，Provides是方法，Module和Provides一般共同出现，工厂和工厂方法，生产出接口或者第三方引用库的依赖注入的对象
 *  此处通过依赖注入，Module工厂生产出返回SccService接口的对象（返回实现类SccServiceImpl的对象）
 */
@Module
class SccModule {
    @Provides
    fun provideSccService(service: SccServiceImpl): SccService {
        return service
    }
}