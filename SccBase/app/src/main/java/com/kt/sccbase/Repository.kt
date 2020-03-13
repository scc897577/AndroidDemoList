package com.kt.sccbase

import com.kt.lib_base.data.net.RetrofitFactory
import com.kt.lib_base.data.protocol.BaseResp
import com.kt.lib_base.utils.JsonUtil
import io.reactivex.Observable
import javax.inject.Inject

class Repository @Inject constructor() {

    fun test(appKey: String, type: String): Observable<BaseResp<TestResp>> {
        val map = HashMap<String, String>()
        map["appKey"] = "78426d8168d83a7b3739b10331462959"
        map["type"] = "shishang"
        return RetrofitFactory.instance.create(Api::class.java).baseTestConnection(JsonUtil.mapToJson())
    }
}