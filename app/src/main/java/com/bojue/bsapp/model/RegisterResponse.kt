package com.bojue.bsapp.model

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
data class RegisterResponse (val username:String,val password:String,
                             val number:String?,val id : Int,
                             val classname : String?,val icon :String?,
                             val phone:String?,val signature:String?)
//"username": "13420117880",
//"password": "123",
//"number": null,//学号
//"id": 4,
//"classname": null,//专业
//"icon": null,
//"nickname": null,
//"phone": null,
//"signature": null