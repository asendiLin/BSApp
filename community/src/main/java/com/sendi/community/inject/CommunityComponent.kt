package com.sendi.community.inject

import android.app.Application
import com.bojue.core.component.BaseComponent
import com.bojue.core.module.CommonModule
import com.sendi.community.viewmodel.CommunityViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * create data : 2019/7/3
 * author : sendi
 * description :
 */
@Singleton
@Component(modules = [CommunityServiceModule::class,CommunityViewModelModule::class,CommonModule::class])
interface CommunityComponent : BaseComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build():CommunityComponent
    }

}