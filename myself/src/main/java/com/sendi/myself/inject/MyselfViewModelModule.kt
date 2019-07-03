package com.sendi.myself.inject

import android.arch.lifecycle.ViewModelProvider
import com.bojue.core.viewmodel.AppViewModelFactory
import com.bojue.core.viewmodel.IViewModel
import com.bojue.core.viewmodel.ViewModelKey
import com.sendi.myself.viewmodel.EditInfoViewModel
import com.sendi.myself.viewmodel.SettingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * create data : 2019/7/3
 * author : sendi
 * description :
 */
@Module
interface MyselfViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SettingViewModel::class)
    fun settingViewModel(viewModel: SettingViewModel): IViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditInfoViewModel::class)
    fun editInfoViewModel(viewModel: EditInfoViewModel): IViewModel

}