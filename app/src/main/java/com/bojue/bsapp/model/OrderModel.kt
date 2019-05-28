package com.bojue.bsapp.model

/**
 * author: asendi.
 * data: 2019/5/16.
 * description:
 */
data class OrderModel(val id:Int,val studentId :Int,
                      val type :Int,val time:String,
                      val status : Int,val studentedId:Int?,
                      val money:Int,val address:String,
                      val phone:String,val content :String,
                      val student :UserModel?,val studented :UserModel?)