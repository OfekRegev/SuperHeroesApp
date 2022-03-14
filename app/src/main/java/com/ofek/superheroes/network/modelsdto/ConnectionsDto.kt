package com.ofek.superheroes.network.modelsdto

import com.google.gson.annotations.SerializedName

data class ConnectionsDto (

	@SerializedName("group-affiliation") val groupAffiliation : String,
	@SerializedName("relatives") val relatives : String
)