package com.kt.lib_base.presenter.view

/*
    MVP中视图回调 基类
 */
interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(text:String)
    fun onDataIsNull(){}//默认实现
}
