package com.kt.handwritingglide

import android.graphics.Bitmap

/*
 * @author : created by CC
 * time    : 2019/9/24  11:06
 * desc    : 
 */

interface RequestListener {

    fun onSuccess(mBitmap: Bitmap)

    fun onFailure()

}