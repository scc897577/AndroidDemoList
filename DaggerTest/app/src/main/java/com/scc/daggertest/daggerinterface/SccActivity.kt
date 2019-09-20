package com.scc.daggertest.daggerinterface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.scc.daggertest.R
import kotlinx.android.synthetic.main.activity_scc.*
import javax.inject.Inject

class SccActivity : AppCompatActivity() {

    //接口类型变量声明@Inject标注
    @Inject
    lateinit var mSccService: SccService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scc)
        initInjection()

        mTvScc.text = mSccService.getSccInfo()
    }

    private fun initInjection() {
        DaggerSccComponent.builder().sccModule(SccModule()).build().inject(this)
    }
}
