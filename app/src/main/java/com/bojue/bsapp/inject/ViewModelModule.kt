package com.bojue.bsapp.inject

import android.arch.lifecycle.ViewModelProvider
import com.bojue.bsapp.community.CommunityViewModel
import com.bojue.bsapp.viewmodel.AppViewModelFactory
import com.bojue.bsapp.viewmodel.IViewModel
import com.bojue.bsapp.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * create data : 2019/5/8
 * author : sendi
 * description :用于提供ViewModel。
 */
@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CommunityViewModel::class)
    fun communityViewModel(viewModel: CommunityViewModel):IViewModel
}