package com.bojue.bsapp.inject

import android.arch.lifecycle.ViewModelProvider
import com.bojue.bsapp.viewmodel.AppViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * create data : 2019/5/8
 * author : sendi
 * description :用于提供ViewModel。
 */
@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

}