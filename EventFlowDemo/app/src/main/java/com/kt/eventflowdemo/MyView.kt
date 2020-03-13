package com.kt.eventflowdemo

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class MyView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val LOG = "SccTouchDemo--->   "

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d(LOG, "MyView :  dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d(LOG, "MyView :  onTouchEvent")
        return super.onTouchEvent(event)
    }

}