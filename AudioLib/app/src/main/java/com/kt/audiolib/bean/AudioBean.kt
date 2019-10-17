package com.kt.audiolib.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
 * @author : created by CC
 * time    : 2019/10/15  14:21
 * desc    : 
 */
@Parcelize
data class AudioBean(var mUrl: String, var id: String):Parcelable