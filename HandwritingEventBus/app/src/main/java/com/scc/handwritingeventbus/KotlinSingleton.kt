package com.scc.handwritingeventbus

/**
 * kotlin实现单例的五种模式
 */
class KotlinSingleton {

    /**
     * 饿汉
     */
    companion object {
        val instance = KotlinSingleton()
    }

   /* *//**
     * 懒汉
     *//*
    companion object {
        private var instance: KotlinSingleton? = null
            get() {
                if (field == null) {
                    field = KotlinSingleton()
                }
                return field
            }

        fun get(): KotlinSingleton {
            return instance!!
        }
    }

    *//**
     * 线程安全的懒汉
     *//*
    companion object {
        private var instance: KotlinSingleton? = null
            get() {
                if (field == null) {
                    field = KotlinSingleton()
                }
                return field
            }

        @Synchronized
        fun get(): KotlinSingleton {
            return instance!!
        }

    }


    *//**
     * 双重校验
     *//*
    companion object {
        val instance: KotlinSingleton by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            KotlinSingleton()
        }
    }

    *//**
     * 静态内部类
     *//*
    companion object {
        val instance = KotlinSingleton.holder
    }

    private object KotlinSingleton {
        val holder = KotlinSingleton()
    }*/
}