package com.kt.sccbase

import com.kt.lib_base.presenter.view.BaseView

interface MainView : BaseView {
    fun onTest(res: TestBean)
}