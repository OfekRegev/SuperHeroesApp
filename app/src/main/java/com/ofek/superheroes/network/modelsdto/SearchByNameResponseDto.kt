package com.ofek.superheroes.network.modelsdto

import com.google.gson.annotations.SerializedName
import com.ofek.superheroes.network.modelsdto.SuperHeroDto


data class SearchByNameResponseDto(
	@SerializedName("response") val response: String,
	@SerializedName("results-for") val resultsFor: String,
	@SerializedName("results") val results: List<SuperHeroDto>
)