package com.kt.eventflowdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent

/**
 * 事件分发机制验证代码
 * 1、验证事件传递流程
 * 2、验证cancel事件
 * 3、验证ViewGroup的事件拦截
 */
class MainActivity : AppCompatActivity() {


    /**
     * 事件的默认传递流程
     * MainActivity :  dispatchTouchEvent               分发
     * -> MyViewGroup :  dispatchTouchEvent             分发
     * -> MyViewGroup :  onInterceptTouchEvent          默认返回false 不拦截
     * -> MyView :  dispatchTouchEvent                  分发
     * -> MyView :  onTouchEvent                        如果没有实现TouchListener  默认返回false 不拦截
     * -> MyViewGroup :  onTouchEvent                   默认返回false 不拦截
     * -> MainActivity :  onTouchEvent                  事件完毕，最终结束
     *
     * 同一个时间序列，如果子View（ViewGroup）没有处理该事件（没有消费事件），
     * 那么后续事件将不会再传递到子View（ViewGroup）  MainActivity :  dispatchTouchEvent  ->  MainActivity :  onTouchEvent
     *
     * cancel事件，scrollView中button，按下button来回滑动，此时scrollView的intercepttouchEvent 拦截了事件，因此button事件为cancel
     *
     * ViewGroup拦截事件
     * 在ViewGroup的onInterceptTouchEvent方法中返回true
     * 在ViewGroup的onTouchEvent方法中返回true并且进行事件处理
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    val LOG = "SccTouchDemo--->   "

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d(LOG, "MainActivity :  dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d(LOG, "MainActivity :  onTouchEvent")
        return super.onTouchEvent(event)
    }
}
