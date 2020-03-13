package com.kt.sccbase

import io.reactivex.Observable

interface BaseService {
    fun test(appKey: String, type: String): Observable<TestBean>
}