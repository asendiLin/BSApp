package com.bojue.bsapp.model

/**
 * author: asendi.
 * data: 2019/5/11.
 * description:
 */
data class CommunityModel (val id : Int,
                           val pic : String,
                           val content : String,
                           val likes : Int?,
                           val origin: String?,
                           val time : String)