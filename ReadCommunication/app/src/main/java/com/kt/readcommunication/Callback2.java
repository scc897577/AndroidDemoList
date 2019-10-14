package com.kt.readcommunication;
/*
 * @author : created by CC
 * time    : 2019/10/14  10:02
 * desc    :
 */

public abstract class Callback2 implements Callback {

    /**
     * 申请失败<br/>
     * 新增授权失败项
     */
    public void failure(String[] permissions) {}

    @Override
    public void failure() {}

}
