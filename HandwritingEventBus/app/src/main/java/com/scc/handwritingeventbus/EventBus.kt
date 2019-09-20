package com.scc.handwritingeventbus

import android.util.Log


/**
 *  author: scc
 *  date: 2019/7/11   9:49
 *  desc: 手写EventBus
 */
class EventBus {

    /**
     * 将传递进来的对象和所有的注解方法存放在map集合中
     */
    private var mCacheMap = HashMap<Any, MutableList<SubscribleMethod>?>()

    /**
     * 双重校验的EventBus单例模式
     */
    companion object {
        val instance: EventBus by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { EventBus() }
    }


    /**
     * EventBus 的注册方法
     * 寻找到any中所有的带有@subscrible的方法
     */
    fun register(any: Any) {
        var list = mCacheMap[any]
        if (list.isNullOrEmpty()) {
            list = findSubscribleMethod(any)
            mCacheMap[any] = list
        }
    }

    /**
     * 找到any中所有的用到@subscrible的注解的方法
     * clazz.declaredMethods        找到当前类的所有的方法
     * clazz.methods                找到当前类和其父类的所有的方法
     *  var subscrible = it.getAnnotation(Subscrible::class.java)   -->  运行期注解 -->  runtime 会被加载进虚拟机
     *  var override = it.getAnnotation(Override::class.java)       -->  编译期注解 -->  source  不会去加载进虚拟机
     */
    private fun findSubscribleMethod(any: Any): MutableList<SubscribleMethod>? {
        val list = mutableListOf<SubscribleMethod>()
        var clazz: Class<Any?>? = any.javaClass as Class<Any?>?     //得到any的反射对象
        //循环遍历反射对象
        while (clazz != null) {
            //对其系统级别父类不进行遍历
            if (clazz.name.startsWith("java.") || clazz.name.startsWith("javax.") || clazz.name.startsWith("android.")) break
            //getmethods得到所有的方法
            val methods = clazz.methods
            //遍历得到所有有Subscrible的注解的方法
            methods.forEach {
                //只找带有Subscrible注解的方法
                val subscrible = it.getAnnotation(Subscrible::class.java) ?: return@forEach
                //var override = it.getAnnotation(Override::class.java)
                //判断带有Subscribe注解的方法中参数的类型，方法的getparametertypes得到所有参数类型
                val types = it.parameterTypes
                if (types.size != 1) {
                    Log.e("Error", "EventBus 只能有一个参数！！！")
                }

                val threadMode = subscrible.threadMode
                val subscribleMethod = SubscribleMethod(it, threadMode, types[0])
                list.add(subscribleMethod)
            }
            clazz = clazz.superclass
        }
        return list
    }

    /**
     *
     */
    fun post(type: Any) {
        //循环mCacheMap中的方法
        for ((any, method) in mCacheMap) {
            if (method.isNullOrEmpty().not()) {
                method!!.forEach {
                    if (it.type.isAssignableFrom(type.javaClass)) {
                        invoke(it, any, type)
                    }
                }
            }
        }
    }

    private fun invoke(subscribleMethod: SubscribleMethod, any: Any, type: Any) {
        val method = subscribleMethod.getmMethod()
        method.invoke(any, type)            //////如果method代表了一个方法，那么调用它的invoke就相当于执行了它所代表的这个方法！！！！！！
    }

}