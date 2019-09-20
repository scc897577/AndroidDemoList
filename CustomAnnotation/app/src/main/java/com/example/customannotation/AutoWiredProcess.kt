package com.example.customannotation

/*
 * @author : created by scc
 * time : 2019/7/15  15:28
 * desc :
 * declaredFields -->   返回当前类所有申明的字段，即包括public、private和protected，不包括父类
 * fields         -->   返回当前类所有public的字段，包括父类
 */

class AutoWiredProcess {
    companion object {

        fun bind(any: Any) {
            //获得对象的所有的变量,以数组的形式存储
            val fields = any.javaClass.declaredFields
            //遍历此对象中所有的变量
            for (field in fields) {
                //每个变量是否有AutoWired的这个注解
                val autoWiredAnnotation = field.getAnnotation(AutoWired::class.java)
                if (autoWiredAnnotation != null) {
                    //isAccessible = true 可以访问私有成员变量
                    field.isAccessible = true
                    //Field.getType()方法返回一个Class对象,用于标识此Field对象所表示的字段的声明类型。
                    val clazz = field.type
                    //得到此字段的声明类型的构造方法
                    val constructor = clazz.getConstructor()
                    //将指定对象变量上此 Field 对象表示的字段设置为指定的新值.将any中file的字段所表示的对象设置为已经实例化之后的实例
                    field.set(any, constructor.newInstance())
                }
            }

        }
    }
}