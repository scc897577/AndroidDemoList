package com.kt.sccbase

import com.kt.lib_base.ext.convert
import io.reactivex.Observable
import javax.inject.Inject

class ServiceImpl @Inject constructor(): BaseService {

    @Inject
    lateinit var repository: Repository

    override fun test(appKey: String, type: String): Observable<TestResp> {
        return repository.test(appKey, type).convert()
    }
}