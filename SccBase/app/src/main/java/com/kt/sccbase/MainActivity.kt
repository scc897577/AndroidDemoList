package com.kt.sccbase

import android.os.Bundle
import android.util.Log
import com.kt.lib_base.ui.activity.BaseMvpActivity
import com.kt.sccbase.injection.DaggerUserComponent
import com.kt.sccbase.injection.UserModule

class MainActivity : BaseMvpActivity<MainPresenter>(), MainView {


    val appKey = "78426d8168d83a7b3739b10331462959"
    val type = "top"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter.test(appKey, type)
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule())
            .build().inject(this)
        mPresenter.mView = this
    }

    override fun onTest(res: TestResp) {
        Log.e("scc", res.toString())
    }


}
