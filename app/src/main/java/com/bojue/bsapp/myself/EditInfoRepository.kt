package com.bojue.bsapp.myself

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.bojue.bsapp.http.api.MyselfService
import com.bojue.bsapp.model.BaseResponse
import com.bojue.bsapp.model.UserModel
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/23.
 * description:
 */
class EditInfoRepository @Inject constructor(val service : MyselfService) {

    val editInfoLiveData = MutableLiveData<BaseResponse<UserModel>>()

    fun editInfo( stuId: Int,  username: String,
                  password: String,  number: String?,
                  classname: String?,  icon: String?,
                  nickname: String?,  phone: String?,
                  signature: String?): LiveData<BaseResponse<UserModel>> {



        return editInfoLiveData
    }

}