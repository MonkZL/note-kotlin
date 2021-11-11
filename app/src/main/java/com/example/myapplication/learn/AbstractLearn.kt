package com.example.myapplication.learn

abstract class BaseActivity {
    fun onCreate() {
        setContentView(getLayoutID())
        initView()
        initData()
    }

    private fun setContentView(layoutID: Int) = println("加载($layoutID)xml到布局中")
    abstract fun getLayoutID(): Int
    abstract fun initView()
    abstract fun initData()
}

class MainActivity : BaseActivity() {

    override fun getLayoutID(): Int {
        return 1
    }

    override fun initView() = println("子类initView")

    override fun initData() = println("子类initData")

}

fun abstractLearn() {
    MainActivity().onCreate()
}