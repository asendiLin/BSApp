package com.sendi.user_export.model

import android.os.Parcel
import android.os.Parcelable

/**
 * author: asendi.
 * data: 2019/5/24.
 * description:
 */
data class UserModel(val id: Int, val username: String,
                     val password: String, var number: String?,
                     val classname: String?, var icon: String?,
                     var nickname: String?, var phone: String?,
                     var signature: String?) :Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(id)
        dest?.writeString(username)
        dest?.writeString(password)
        dest?.writeString(number)
        dest?.writeString(classname)
        dest?.writeString(icon)
        dest?.writeString(nickname)
        dest?.writeString(phone)
        dest?.writeString(signature)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }
}