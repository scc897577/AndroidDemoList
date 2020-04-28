package com.kt.ffmpegdemo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 此项目是Java调用C的两种方式
 */

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example of a call to a native method
        sample_text.text = stringFromJNI() + stringFromJNI2() + stringFromJNI3("ccc") + _test()
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
            关键字external
            在Java里面标识一个方法是JNI方法,使用关键字native
            但在Kotlin里面使用external关键字
     */
    external fun stringFromJNI(): String
    //自己的Native 方法，JNI
    external fun stringFromJNI2(): String
    //有参数的native方法
    external fun stringFromJNI3(str:String): String



    //方法二
    external fun _test():String
    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
