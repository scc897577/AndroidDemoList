package com.kt.handwritingglide

import android.content.Context
import android.widget.ImageView
import java.lang.ref.SoftReference
import java.lang.ref.WeakReference

/*
 * @author : created by CC
 * time    : 2019/9/24  10:21
 * desc    : 代表每一张图片的请求对象
 */

class BitmapRequest constructor(var mContext: Context) {

    //图片请求地址
    var url: String? = null

    //需要加载控件
    var mImageView: SoftReference<ImageView>? = null

    //展位图资源ID
    var mResId: Int? = null

    //回调对象
    var mRequestListener: RequestListener? = null

    //图片的标识符 --> 解决图片错乱
    var urlMd5: String? = null

    /** 设置URL */
    fun load(url: String): BitmapRequest {
        this.url = url
        this.urlMd5 = Md5Util.toMD5(url)
        return this
    }

    /** 设置占位图片 */
    fun loading(mResId: Int): BitmapRequest {
        this.mResId = mResId
        return this
    }

    /** 设置监听 */
    fun listener(mRequestListener: RequestListener): BitmapRequest {
        this.mRequestListener = mRequestListener
        return this
    }

    /** 设置显示图片的控件 */
    fun into(mImageView: ImageView) {
        mImageView.tag = urlMd5
        this.mImageView = SoftReference(mImageView)
    }
}