package com.ofek.superheroes.network.modelsdto

import com.google.gson.annotations.SerializedName


data class AppearanceDto(
    @SerializedName("gender") val gender: String? = null,
    @SerializedName("race") val race: String? = null,
    @SerializedName("height") val height: List<String>? = null,
    @SerializedName("weight") val weight: List<String>? = null,
    @SerializedName("eye-color") val eyeColor: String? = null,
    @SerializedName("hair-color") val hairColor: String? = null
)