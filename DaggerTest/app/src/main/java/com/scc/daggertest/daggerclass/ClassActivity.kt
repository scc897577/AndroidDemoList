package com.scc.daggertest.daggerclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.scc.daggertest.R
import kotlinx.android.synthetic.main.activity_class.*
import javax.inject.Inject

class ClassActivity : AppCompatActivity() {

    /**
     * 此处@Inject为将要找到DaggerPerson类的实例对象
     * 在DaggerPerson类中@Inject标注了构造方法表示实例对象将要完成
     * 在此处@Inject标注了DaggerPerson的将要被引用的实例对象
     * 通过component这个桥梁将DaggerPerson的实例对象和此处的引用连接起来
     */
    @Inject
    lateinit var mDaggerPerson: DaggerPerson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class)

        initInjection()

        mTvClass.text = mDaggerPerson.printLog()


    }


    /**
     * 注解引用类的构造方法之后
     * 注解引用类的对象引用之后
     * 编写了component的桥梁之后
     * 进行编译
     * 之后通过DaggerPersonComponent的build方法将类的实例化和类的实例化的引用连接起来
     */
    private fun initInjection() {
        DaggerPersonComponent.builder().build().inject(this)
    }
}

