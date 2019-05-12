package com.bojue.core.common

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window

/**
 * author: asendi.
 * data: 2019/5/6.
 * description:Activity的公共类，后续Activity继承此类
 */
open class BaseActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}