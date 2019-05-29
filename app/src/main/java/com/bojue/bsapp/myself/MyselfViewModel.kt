package com.bojue.bsapp.myself

import android.app.Application
import com.bojue.bsapp.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/10.
 * description:
 */
class MyselfViewModel @Inject constructor(application: Application,val repository: EditInfoRepository)
    : BaseViewModel(application){

    val  editInfoLiveData = repository.editInfoLiveData

    fun editInfo(stuId: Int,  username: String,
                 password: String,  number: String?,
                 classname: String?,  icon: String?,
                 nickname: String?,  phone: String?,
                 signature: String?) {
        repository.editInfo(stuId, username, password, number, classname, icon, nickname, phone, signature)
    }
}