package com.kt.handwritingglide

import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import java.io.FileOutputStream
import java.util.concurrent.LinkedBlockingQueue

/*
 * @author : created by CC
 * time    : 2019/9/24  11:09
 * desc    : 
 */

class BitmapDispatcher constructor(var mRequestQueue: LinkedBlockingQueue<BitmapRequest>) : Thread() {

    //LinkedBlockingQueue --> 线程安全，先进先出


    private var handler = Handler(Looper.getMainLooper())

    override fun run() {
        super.run()
        //做操作      isInterrupted--->测试当前线程是否已经中断
        while (!isInterrupted) {
            //从队列里获取请求
            val br = mRequestQueue.take()
            //设置占位图片
            showLoadingIma(br)
            //从服务器加载图片
            val bitmap = findBitmap(br)
            //将图片显示到ImageView中
            showImageView(br, bitmap)
        }
    }

    private fun showImageView(br: BitmapRequest?, bitmap: Bitmap?) {

    }

    private fun findBitmap(br: BitmapRequest?): Bitmap? {
        if (br != null) {
            return downLoadImage(br.url)
        }
        return null
    }

    private fun downLoadImage(url: String?): Bitmap? {
        TODO()
    }

    private fun showLoadingIma(br: BitmapRequest?) {
        if (br != null && br.mResId!! > 0 && br.mImageView != null) {
            val redId = br.mResId!!
            val imageView = br.mImageView!!.get()
            handler.post {
                imageView?.setImageResource(redId)
            }
        }
    }
}