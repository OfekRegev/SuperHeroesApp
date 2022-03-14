package com.ofek.superheroes.searchscreen.ui.models

import android.os.Parcel
import android.os.Parcelable

data class SearchScreenSuperHeroModel(
    val id: Int? = null,
    val name: String? = null,
    val imageUrl: String? = null,
    val aliases: String? = null,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
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
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchScreenSuperHeroModel> {
        override fun createFromParcel(parcel: Parcel): SearchScreenSuperHeroModel {
            return SearchScreenSuperHeroModel(parcel)
        }

        override fun newArray(size: Int): Array<SearchScreenSuperHeroModel?> {
            return arrayOfNulls(size)
        }
    }

}