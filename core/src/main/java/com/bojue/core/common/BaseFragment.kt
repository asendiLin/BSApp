package com.bojue.core.common

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.bojue.core.component.BaseComponent


/**
 * author: asendi.
 * data: 2019/5/6.
 * description:Fragment的公共类，后续Fragment继承此类.
 */
open class BaseFragment : Fragment(){
    val mViewModelFactory by lazy {
        viewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
    }

    open fun viewModelFactory():ViewModelProvider.Factory{
        return BaseComponent.instance.provideViewModelFactory()
    }
}