package com.bojue.bsapp.inject

import android.arch.lifecycle.ViewModelProvider
import com.bojue.bsapp.community.CommunityViewModel
import com.bojue.bsapp.community.MyselfCommunityViewModel
import com.bojue.bsapp.course.CourseViewModel
import com.bojue.bsapp.login.LoginViewModel
import com.bojue.bsapp.myself.EditInfoViewModel
import com.bojue.bsapp.myself.MyselfViewModel
import com.bojue.bsapp.publish.PublishOrderViewModel
import com.bojue.bsapp.publish.PublishViewModel
import com.bojue.bsapp.register.RegisterViewModel
import com.bojue.bsapp.setting.SettingViewModel
import com.bojue.core.viewmodel.AppViewModelFactory
import com.bojue.core.viewmodel.IViewModel
import com.bojue.core.viewmodel.ViewModelKey
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

    @Binds
    @IntoMap
    @ViewModelKey(MyselfViewModel::class)
    fun myselfViewModel(viewModel: MyselfViewModel):IViewModel


    @Binds
    @IntoMap
    @ViewModelKey(PublishViewModel::class)
    fun publishViewModel(viewModel: PublishViewModel):IViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun loginViewModel(viewModel: LoginViewModel):IViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    fun registerViewModel(viewModel: RegisterViewModel):IViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingViewModel::class)
    fun settingViewModel(viewModel: SettingViewModel):IViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditInfoViewModel::class)
    fun editInfoViewModel(viewModel: EditInfoViewModel):IViewModel


    @Binds
    @IntoMap
    @ViewModelKey(PublishOrderViewModel::class)
    fun publishOrderViewModel(viewModel: PublishOrderViewModel):IViewModel



    @Binds
    @IntoMap
    @ViewModelKey(MyselfCommunityViewModel::class)
    fun selfCommunityViewModel(viewModel: MyselfCommunityViewModel):IViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CourseViewModel::class)
    fun courseViewModel(viewModel: CourseViewModel):IViewModel

}