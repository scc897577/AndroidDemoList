package com.kt.audiolib.app;
/*
 * @author : created by CC
 * time    : 2019/10/15  14:00
 * desc    : 唯一与外界通信的帮助类
 */

import android.annotation.SuppressLint;
import android.content.Context;

public class AudioHelper {

    //SDK全局Context, 供子模块用
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static Context getContext() {
        return mContext;
    }

}

