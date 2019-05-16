package com.bojue.core.component

import android.arch.lifecycle.ViewModelProvider

/**
 * author: asendi.
 * data: 2019/5/16.
 * description:
 */
interface BaseComponent {

    fun  provideViewModelFactory() : ViewModelProvider.Factory

    companion object {
        lateinit var instance : BaseComponent
    }
}