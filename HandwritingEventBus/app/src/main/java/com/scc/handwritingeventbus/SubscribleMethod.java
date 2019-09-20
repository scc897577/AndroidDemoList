package com.scc.handwritingeventbus;

import java.lang.reflect.Method;

/**
 * author: scc
 * date: 2019/7/11   13:48
 * desc: 带有注解的方法实体封装类
 */
public class SubscribleMethod {
    //方法体本身
    private Method mMethod;
    //线程模式
    private ThreadMode mThreadMode;
    //回调方法中参数的类型
    private Class<?> type;



    public Method getmMethod() {
        return mMethod;
    }

    public void setmMethod(Method mMethod) {
        this.mMethod = mMethod;
    }

    public ThreadMode getmThreadMode() {
        return mThreadMode;
    }

    public void setmThreadMode(ThreadMode mThreadMode) {
        this.mThreadMode = mThreadMode;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public SubscribleMethod(Method mMethod, ThreadMode mThreadMode, Class<?> type) {
        this.mMethod = mMethod;
        this.mThreadMode = mThreadMode;
        this.type = type;
    }
}
