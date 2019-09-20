package com.scc.handwritingeventbus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

/**
 * MainActivity当中有方法XXX，SecondActivity需要调用MainActivity当中的方法XXX
 * 用EventBus作为传递者，需要将MainActivity中的XXX方法添加到EventBus中去
 * 但并不是将MainActivity中的所有的方法传递到EventBus中去
 * 而是所有带有自定义注解的方法都放进去(自定义注解@Subscrible)
 * @Subscrible  相当于给方法添加了一个标记
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EventBus.instance.register(this)
        TextView.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }


    @Subscrible(threadMode = ThreadMode.MAIN)
    fun test(eb: EventBean) {
        Log.e("---->", eb.toString())
    }
}
