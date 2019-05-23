package com.bojue.bsapp.model

/**
 * author: asendi.
 * data: 2019/5/11.
 * description:
 */
data class CommunityModel (val id : Int,
                           val studentId : Int,
                           val pic : String,
                           val content : String,
                           val likes : Int?,
                           val origin: String?,
                           val nickname:String?,
                           val icon : String?,
                           val signature:String?,
                           val time : String)