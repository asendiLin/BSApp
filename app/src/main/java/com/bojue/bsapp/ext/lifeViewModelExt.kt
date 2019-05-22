package com.bojue.bsapp.ext

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.bojue.bsapp.viewmodel.BaseViewModel
import com.bojue.bsapp.viewmodel.IViewModel
import com.bojue.core.common.BaseActivity
import com.bojue.core.common.BaseFragment

/**
 * author: asendi.
 * data: 2019/5/18.
 * description:
 */
fun <T : BaseViewModel> FragmentActivity.getViewModel(clazz : Class<T>):T{
    return if (this is BaseActivity){
        ViewModelProviders.of(this,mViewModelFactory).get(clazz)
    }else{
        ViewModelProviders.of(this).get(clazz)
    }
}

fun <T : BaseViewModel> Fragment.getViewModel(clazz : Class<T>):T{
    return if (this is BaseFragment){
        ViewModelProviders.of(this,mViewModelFactory).get(clazz)
    }else{
        ViewModelProviders.of(this).get(clazz)
    }
}

