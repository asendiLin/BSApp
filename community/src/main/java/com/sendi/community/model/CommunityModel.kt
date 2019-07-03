package com.sendi.community.model

import android.os.Parcel
import android.os.Parcelable

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
                           var nickname:String?,
                           var icon : String?,
                           val signature:String?,
                           val isLike : String?,
                           val time : String) : Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
            )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(id)
        dest?.writeInt(studentId)
        dest?.writeString(pic)
        dest?.writeString(content)
        dest?.writeValue(likes)
        dest?.writeString(origin)
        dest?.writeString(nickname)
        dest?.writeString(icon)
        dest?.writeString(signature)
        dest?.writeString(isLike)
        dest?.writeString(time)
    }

    override fun describeContents(): Int {
        return  0
    }

    companion object CREATOR : Parcelable.Creator<CommunityModel> {
        override fun createFromParcel(parcel: Parcel): CommunityModel {
            return CommunityModel(parcel)
        }

        override fun newArray(size: Int): Array<CommunityModel?> {
            return arrayOfNulls(size)
        }
    }
}