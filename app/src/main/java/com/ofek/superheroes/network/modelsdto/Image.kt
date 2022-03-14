package com.ofek.superheroes.network.modelsdto

import com.google.gson.annotations.SerializedName


data class Image(

    @SerializedName("url") val url: String? = null
)