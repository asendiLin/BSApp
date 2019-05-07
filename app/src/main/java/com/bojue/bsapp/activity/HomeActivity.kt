package com.bojue.bsapp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bojue.bsapp.R
import com.bojue.core.common.BaseActivity

/**
 * 用于存放首页四个tab对应的Fragment
 */
class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}