package com.bojue.core.common

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import com.alibaba.android.arouter.launcher.ARouter
import com.bojue.core.component.BaseComponent
import com.bojue.core.util.ActivityManger

/**
 * author: asendi.
 * data: 2019/5/6.
 * description:Activity的公共类，后续Activity继承此类
 */
abstract class BaseActivity : AppCompatActivity(){
    val mViewModelFactory by lazy {
       requireViewModelFactory()
    }

    open fun requireViewModelFactory(): ViewModelProvider.Factory{
        return BaseComponent.instance.provideViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityManger.addActivity(this)
        ARouter.getInstance().inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityManger.removeActivity(this)
    }

}