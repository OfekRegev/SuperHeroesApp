package com.ofek.superheroes.herodetailsscreen.ui.models

import android.os.Parcel
import android.os.Parcelable

data class DetailsScreenSuperheroModel(
    val id: Int? = null,
    val name: String? = null,
    val image: String? = null,
    val biography: String? = null,
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
        parcel.writeString(image)
        parcel.writeString(biography)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DetailsScreenSuperheroModel> {
        override fun createFromParcel(parcel: Parcel): DetailsScreenSuperheroModel {
            return DetailsScreenSuperheroModel(parcel)
        }

        override fun newArray(size: Int): Array<DetailsScreenSuperheroModel?> {
            return arrayOfNulls(size)
        }
    }

}