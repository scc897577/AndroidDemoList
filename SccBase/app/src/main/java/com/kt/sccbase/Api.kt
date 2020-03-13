package com.kt.sccbase

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST("toutiao/index")
    fun getNews(@Body body: TestReq): Observable<TestBean>

}