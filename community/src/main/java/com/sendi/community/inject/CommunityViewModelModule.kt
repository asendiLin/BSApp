package com.sendi.community.inject

import android.arch.lifecycle.ViewModelProvider
import com.bojue.core.viewmodel.AppViewModelFactory
import com.bojue.core.viewmodel.IViewModel
import com.bojue.core.viewmodel.ViewModelKey
import com.sendi.community.viewmodel.CommunityViewModel
import com.sendi.community.viewmodel.MyselfCommunityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * create data : 2019/7/3
 * author : sendi
 * description :
 */
@Module
interface CommunityViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(com.sendi.community.viewmodel.CommunityViewModel::class)
    fun communityViewModel(viewModel: CommunityViewModel): IViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MyselfCommunityViewModel::class)
    fun selfCommunityViewModel(viewModel: MyselfCommunityViewModel):IViewModel
}