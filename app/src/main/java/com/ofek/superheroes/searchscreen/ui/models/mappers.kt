package com.ofek.superheroes.searchscreen.ui.models

import com.ofek.superheroes.network.modelsdto.SuperHeroDto

fun mapSuperHeroDtoToUiHeroModel(superHeroDto: SuperHeroDto): SearchScreenSuperHeroModel {
    return SearchScreenSuperHeroModel(
        superHeroDto.id,
        superHeroDto.name,
        superHeroDto.image?.url,
        superHeroDto.biography?.aliases?.joinToString(", "),
    )
}
