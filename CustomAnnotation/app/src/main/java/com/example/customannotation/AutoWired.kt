package com.example.customannotation

import java.lang.annotation.ElementType

/*
 * @author : created by scc
 * time : 2019/7/15  14:24
 * desc : 自定义注解，主要作用--> 用来自动生成对象
 */

@Target(AnnotationTarget.FIELD)             //作用于变量之上
@Retention(AnnotationRetention.RUNTIME)     //注解在运行时有效
annotation class AutoWired