package com.kt.readcommunication;
/*
 * @author : created by CC
 * time    : 2019/10/14  10:01
 * desc    :
 */

@Deprecated
public interface Callback {
    /**
     * 申请成功
     */
    void success();

    /**
     * 申请失败
     */
    @Deprecated
    void failure();
}