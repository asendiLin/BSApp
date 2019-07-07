package com.sendi.base.constance

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
const val DEFAULT_URL = "http:///"
//const val DEFAULT_URL = "http://119.23.78.201/"
//const val DEFAULT_URL = "http://192.168.1.105/"

var BASE_URL= DEFAULT_URL
const val SUCCESS_STATU = 1
const val FAIL_STATU = -1

const val DOING_ORDER = 0x1
const val CANCEL_ORDER = 0x2
const val COMPLETE_ORDER = 0x3
const val HISTORY_ORDER_TYPE = "type"

const val ORDER_DETAIL = "order_detail"

const val BASE_URL_KEY = "base_url"

const val COMMUNITY_LIST = "communityList"

const val UNKNOW_USER = -1

const val POSITION = "position"

 val originType = 0
 val paotui =1
 val daina =2
 val pinche =3
 val huhuan =4
 val shunfengche =5
 val peiban =6
 val other =7

const val ORDER_LIST_PATH = "/order/list"
const val ORDER_PUBLISH_PATH = "/order/publish"
const val COMMUNITY_PATH = "/community/list"
const val MYSELF_PATH = "/myself/page"

const val HOME_ACTIVITY = "/home/home_activity"