package com.bojue.bsapp.model

import android.os.Parcel
import android.os.Parcelable

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
                      val student :UserModel?,val studented :UserModel?) : Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(UserModel::class.java.classLoader),
            parcel.readParcelable(UserModel::class.java.classLoader))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(studentId)
        parcel.writeInt(type)
        parcel.writeString(time)
        parcel.writeInt(status)
        parcel.writeValue(studentedId)
        parcel.writeInt(money)
        parcel.writeString(address)
        parcel.writeString(phone)
        parcel.writeString(content)
        parcel.writeParcelable(student, flags)
        parcel.writeParcelable(studented, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrderModel> {
        override fun createFromParcel(parcel: Parcel): OrderModel {
            return OrderModel(parcel)
        }

        override fun newArray(size: Int): Array<OrderModel?> {
            return arrayOfNulls(size)
        }
    }
}