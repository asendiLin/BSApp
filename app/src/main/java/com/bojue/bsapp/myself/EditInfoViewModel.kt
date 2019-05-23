package com.bojue.bsapp.myself

import android.app.Application
import android.arch.lifecycle.LiveData
import com.bojue.bsapp.model.BaseResponse
import com.bojue.bsapp.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/23.
 * description:
 */
class EditInfoViewModel @Inject constructor(application: Application,val repository: EditInfoRepository)
    : BaseViewModel(application) {

    fun editInfo(): LiveData<BaseResponse<Any?>> {

        return repository.editInfo()
    }

}