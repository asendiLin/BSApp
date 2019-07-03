package com.sendi.myself.model

/**
 * author: asendi.
 * data: 2019/5/29.
 * description:
 */
data class CourseModel(val courseId:Int?,val beginYear:Int?,val endYear:Int?,val term:Int,
                       val teacher :String,val name:String,val classroom:String,val period:String,
                       val week :Int,val sectionStart : Int,val sectionEnd : Int)