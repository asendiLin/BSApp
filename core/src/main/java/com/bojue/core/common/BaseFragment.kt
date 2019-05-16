package com.bojue.core.common

import android.support.v4.app.Fragment
import com.bojue.core.component.BaseComponent


/**
 * author: asendi.
 * data: 2019/5/6.
 * description:Fragment的公共类，后续Fragment继承此类.
 */
open class BaseFragment : Fragment(){
    val mViewModelFactory by lazy {
        BaseComponent.instance.provideViewModelFactory()
    }
}