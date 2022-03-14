package com.ofek.superheroes.uimodels.models

import android.os.Parcel
import android.os.Parcelable

data class UiSuperHeroModel(
    val id: Int? = null,
    val name: String? = null,
    val imageUrl: String? = null,
    val aliases: String? = null,
    val appearance: String? = null,
    val connections: String? = null,
    val work: String? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(imageUrl)
        parcel.writeString(aliases)
        parcel.writeString(appearance)
        parcel.writeString(connections)
        parcel.writeString(work)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UiSuperHeroModel> {
        override fun createFromParcel(parcel: Parcel): UiSuperHeroModel {
            return UiSuperHeroModel(parcel)
        }

        override fun newArray(size: Int): Array<UiSuperHeroModel?> {
            return arrayOfNulls(size)
        }
    }
}