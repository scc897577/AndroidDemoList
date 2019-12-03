package com.kt.okhttpsource

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * 第一步：创建OkHttpClient
 * OkHttpClient 是客户端核心类，通过Builder()内部类来生成Dispatcher分发器，分发器分发同步异步的请求，
 * OkHttpClient 设置完参数之后，通过.build()来生成OkHttpClient的对象
 * 第二步：创建Request
 * Request请求报文，请求头header，请求地址URL，请求方法GET和POST等，也是通过.build方法创建
 * 第三步：Call对象
 * Call为连接 Request 和response的桥梁
 *
 * call进行同步和异步请求（execute同步）（enqueue异步）
 *
 *
 * OKhttp 同步方法总结
 * 1，创建创建OkHttpClient和Request对象
 * 2，将Request封装成Call对象
 * 3，调用call的execute同步方法发送同步请求
 * OKhttp同步需要注意
 * 发送请求之后，就会进入阻塞状态，直到收到响应
 *
 * OKhttp 同步方法总结
 * 1，创建创建OkHttpClient和Request对象
 * 2，将Request封装成Call对象
 * 3，调用call的enqueue异步方法发送异步请求，callBack 进行回调
 * 需要注意
 * onResponse和onFailure都是在子线程中回调的
 *
 * 同步和异步的区别
 * 1，发起请求的方法调用
 * 2，阻塞线程与否
 *
 *
 * 所有的请求会先放到连接池 ConnectionPool 连接池当中，当是相同的参数和相同的URL的时候，可以直接复用
 *
 *
 *
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var client = OkHttpClient
        .Builder()
        .readTimeout(5, TimeUnit.SECONDS)
        .build()

    fun synRequest() {
        val request = Request
            .Builder()
            .url("http://www.baidu.com")
            .get()
            .build()
        val call = client.newCall(request)
        try {
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call, response: Response) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            })


            val response = call.execute()
            println(response.body().toString())
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}
