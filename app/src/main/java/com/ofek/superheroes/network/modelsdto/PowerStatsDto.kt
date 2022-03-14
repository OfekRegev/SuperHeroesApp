package com.ofek.superheroes.network.modelsdto

import com.google.gson.annotations.SerializedName


data class PowerStatsDto (

	@SerializedName("intelligence") val intelligence : String? = null,
	@SerializedName("strength") val strength : String? = null,
	@SerializedName("speed") val speed : String? = null,
	@SerializedName("durability") val durability : String? = null,
	@SerializedName("power") val power : String? = null,
	@SerializedName("combat") val combat : String? = null
)