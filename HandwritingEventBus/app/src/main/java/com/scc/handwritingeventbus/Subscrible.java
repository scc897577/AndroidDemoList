package com.scc.handwritingeventbus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: scc
 * date: 2019/7/11   13:38
 * desc:
 * Target注解代表注解将要应用类型，ElementType是一个枚举类，里面有文件，方法，类，构造参数，包等
 * Retention 代表是此注解应用区域，有RUNTIME，SOURCE和CLASS
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscrible {

    ThreadMode threadMode() default ThreadMode.MAIN;
}
