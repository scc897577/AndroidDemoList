package com.scc.daggertest.daggerinterface

import javax.inject.Inject

/**
 *  author: scc
 *  date: 2019/7/8   17:41
 *  desc: SccService接口的实现类SccServiceImpl，getSccInfo方法返回String
 */
class SccServiceImpl @Inject constructor() : SccService {
    override fun getSccInfo(): String {
        return "This is scc info!"
    }
}