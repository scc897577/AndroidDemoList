package com.kt.sccbase

import io.reactivex.Observable
import javax.inject.Inject

class ServiceImpl @Inject constructor(): BaseService {

    @Inject
    lateinit var repository: Repository

    override fun test(appKey: String, type: String): Observable<TestBean> {
        return repository.test(appKey, type)
    }
}