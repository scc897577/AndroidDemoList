package com.kt.sccbase

import com.kt.lib_base.data.protocol.BaseResp
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

interface Api {

    //此种方式出来参数Json格式
    //@POST("toutiao/index")
    //fun getNews(@Body body: TestReq): Observable<TestBean>

    @FormUrlEncoded
    @POST("toutiao/index")
    fun getNews(@FieldMap map: Map<String, String>): Observable<TestBean>

    @Headers("Content-Type: application/json", "Accept: application/json")//需要添加头
    @POST("base/testConnection")
    fun baseTestConnection(@Body body: RequestBody): Observable<BaseResp<TestResp>>

}