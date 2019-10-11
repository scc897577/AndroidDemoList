package com.kt.netlib.net

import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/*
 * @author : created by CC
 * time    : 2019/10/10  15:07
 * desc    : Retrofit工厂，单例
 */

class RetrofitFactory private constructor() {

    /** 单例实现 */
    companion object {
        val instance: RetrofitFactory by lazy { RetrofitFactory() }
    }

    private val interceptor: Interceptor
    private val retrofit: Retrofit

    /** 初始化 */
    init {
        /** 通用拦截 */
        interceptor = Interceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Content_Type", "application/json")
                .addHeader("charset", "UTF-8")
//                .addHeader("token",)   此处可以加token
                .build()

            chain.proceed(request)
        }
        /** retrofit实例化 */
        retrofit = Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}