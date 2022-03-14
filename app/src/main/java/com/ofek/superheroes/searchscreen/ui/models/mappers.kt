package com.ofek.superheroes.uimodels.models

import com.ofek.superheroes.network.modelsdto.AppearanceDto
import com.ofek.superheroes.network.modelsdto.ConnectionsDto
import com.ofek.superheroes.network.modelsdto.SuperHeroDto
import com.ofek.superheroes.network.modelsdto.WorkDto

fun mapSuperHeroDtoToUiHeroModel(superHeroDto: SuperHeroDto): UiSuperHeroModel {
    return UiSuperHeroModel(
        superHeroDto.id,
        superHeroDto.name,
        superHeroDto.image?.url,
        superHeroDto.biography?.aliases?.joinToString(", "),
        buildAppearance(superHeroDto.appearanceDto),
        buildConnections(superHeroDto.connections),
        buildWork(superHeroDto.work),
    )
}

fun buildWork(work: WorkDto?): String? {
    return "base work: ${work?.base.orEmpty()}\n" +
            "occupation: ${work?.occupation.orEmpty()}"
}

fun buildConnections(connections: ConnectionsDto?): String? {
    return "group affiliation: ${connections?.groupAffiliation.orEmpty()}\n" +
            "relatives: ${connections?.relatives.orEmpty()}"
}

fun buildAppearance(appearanceDto: AppearanceDto?): String {
    return "eye color: ${appearanceDto?.eyeColor.orEmpty()}" + "\n" +
            "gender: ${appearanceDto?.gender.orEmpty()}" + "\n" +
            "hair color: ${appearanceDto?.hairColor.orEmpty()}" + "\n"
}