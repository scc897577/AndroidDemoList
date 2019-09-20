package com.scc.handwritingeventbus;

import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * author: scc
 * date: 2019/7/11   16:57
 * desc:
 */
public class Test {
    /**
     * private fun findSubscribleMethod(any: Any): MutableList<SubscribleMethod>? {
     * val list = mutableListOf<SubscribleMethod>()
     * val clazz = any.javaClass
     * //        while (clazz != null) {
     * //            if (clazz.name.startsWith("java.") || clazz.name.startsWith("javax.") || clazz.name.startsWith("android."))
     * val methods = clazz.methods
     * methods.forEach {
     * //只找带有Subscrible注解的方法
     * val subscrible = it.getAnnotation(Subscrible::class.java) ?: return@forEach
     * //var override = it.getAnnotation(Override::class.java)
     * //判断带有Subscribe注解的方法中参数的类型
     * val types = it.parameterTypes
     * if (types.size != 1) {
     * Log.e("Error", "EventBus 只能有一个参数！！！")
     * }
     * <p>
     * val threadMode = subscrible.threadMode
     * val subscribleMethod = SubscribleMethod(it, threadMode, types[0])
     * list.add(subscribleMethod)
     * }
     * //            clazz.superclass
     * //        }
     * return list
     * }
     */

    public List<SubscribleMethod> findSubscribleMethod(Object obj) {
        List<SubscribleMethod> list = new ArrayList<SubscribleMethod>();
        Class<?> clazz = obj.getClass();
        while (clazz != null) {
            if (clazz.getName().startsWith("java.") || clazz.getName().startsWith("javax.") || clazz.getName().startsWith("android.")) {
                break;
            }
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                Subscrible subscrible = method.getAnnotation(Subscrible.class);
                if (subscrible == null) {
                    continue;
                }
                Class<?>[] types = method.getParameterTypes();
                if (types.length != 1) {
                    Log.e("Error", "EventBus 只能有一个参数！！！");
                }

                ThreadMode mode = subscrible.threadMode();
                SubscribleMethod subscribleMethod = new SubscribleMethod(method, mode, types[0]);
                list.add(subscribleMethod);
            }
            clazz = clazz.getSuperclass();
        }
        return list;
    }
}
