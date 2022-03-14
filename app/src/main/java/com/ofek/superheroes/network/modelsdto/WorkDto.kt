package com.ofek.superheroes.network.modelsdto

import com.google.gson.annotations.SerializedName

data class WorkDto (
	@SerializedName("occupation") val occupation : String? = null,
	@SerializedName("base") val base : String? = null
)