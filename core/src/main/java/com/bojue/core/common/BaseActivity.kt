package com.bojue.core.common

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import com.bojue.core.component.BaseComponent

/**
 * author: asendi.
 * data: 2019/5/6.
 * description:Activity的公共类，后续Activity继承此类
 */
open class BaseActivity : AppCompatActivity(){
    val mViewModelFactory by lazy {
        BaseComponent.instance.provideViewModelFactory()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}