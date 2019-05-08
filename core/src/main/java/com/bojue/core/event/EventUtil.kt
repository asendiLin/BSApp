package com.bojue.core.event

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.meta.SubscriberInfoIndex

/**
 * create data : 2019/5/8
 * author : sendi
 * description :用于事件发送、注册等相关操作
 */
object EventUtil {

    fun setup(indexes: List<SubscriberInfoIndex>) {
        val builder = EventBus.builder()
        indexes.onEach {
            builder.addIndex(it)
        }
        builder.apply {
            /*如果需要其他自定义配置可再加*/
            logNoSubscriberMessages(true)
            logSubscriberExceptions(true)
            installDefaultEventBus()
            throwSubscriberException(false)
        }
    }

    private fun bus() : EventBus {
        return EventBus.getDefault()
    }

    fun post(event : Any){
        bus().post(event)
    }

    fun postSticky(event: Any){
        bus().postSticky(event)
    }

    fun register(subscriber : Any){
        bus().register(subscriber)
    }

    fun unregister(subscriber : Any){
        bus().unregister(subscriber)
    }

    fun isRegister(subscriber: Any) : Boolean{
        return bus().isRegistered(subscriber)
    }

    fun removeStickyEvent(event : Any){
        bus().removeStickyEvent(event)
    }

    fun <T> removeStickyEvent(eventClassType : Class<T>){
        bus().removeStickyEvent(eventClassType)
    }


}