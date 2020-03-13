package com.kt.sccbase

import com.kt.lib_base.data.net.RetrofitFactory
import io.reactivex.Observable
import javax.inject.Inject

class Repository @Inject constructor() {

    fun test(appKey: String, type: String):Observable<TestBean>{
        return RetrofitFactory.instance.create(Api::class.java).getNews(TestReq(appKey,type))
    }
}