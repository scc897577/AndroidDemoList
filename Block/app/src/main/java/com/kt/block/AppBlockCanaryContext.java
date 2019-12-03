package com.kt.block;

import android.content.Context;
import com.github.moduth.blockcanary.BlockCanaryContext;
import com.github.moduth.blockcanary.internal.BlockInfo;

import java.io.File;

/*
 * @author : created by CC
 * time    : 2019/10/30  11:53
 * desc    :
 */

public class AppBlockCanaryContext extends BlockCanaryContext {

    //默认卡顿阈值为1000ms
    public int provideBlockThreshold() {
        return 1000;
    }

    //输出的log
    public String providePath() {
        return "/blockcanary/";
    }

    //支持文件上传
    public void upload(File zippedFile) {
        throw new UnsupportedOperationException();

    }

    //可以在卡顿提供自定义操作
    @Override
    public void onBlock(Context context, BlockInfo blockInfo) {
        System.out.println("开始时间：" + blockInfo.timeStart + "   结束时间：" + blockInfo.timeEnd);
    }
}