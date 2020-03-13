package com.kt.eventflowdemo

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.FrameLayout

class MyViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    val LOG = "SccTouchDemo--->   "

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d(LOG, "MyViewGroup :  dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.d(LOG, "MyViewGroup :  onInterceptTouchEvent")
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d(LOG, "MyViewGroup :  onTouchEvent")
        return super.onTouchEvent(event)
    }

}