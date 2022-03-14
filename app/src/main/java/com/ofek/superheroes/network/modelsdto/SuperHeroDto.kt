package com.ofek.superheroes.network.modelsdto

import com.google.gson.annotations.SerializedName

data class SuperHeroDto (
    @SerializedName("id") val id : Int? = null,
    @SerializedName("name") val name : String? = null,
    @SerializedName("powerstats") val powerstats : PowerStatsDto? = null,
    @SerializedName("biography") val biography : BiographyDto? = null,
    @SerializedName("appearance") val appearanceDto : AppearanceDto? = null,
    @SerializedName("work") val work : WorkDto? = null,
    @SerializedName("connections") val connections : ConnectionsDto? = null,
    @SerializedName("image") val image : Image? = null,
) {
}