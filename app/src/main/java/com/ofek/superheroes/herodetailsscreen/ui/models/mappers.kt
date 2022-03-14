package com.ofek.superheroes.herodetailsscreen.ui.models

import com.ofek.superheroes.network.modelsdto.AppearanceDto
import com.ofek.superheroes.network.modelsdto.ConnectionsDto
import com.ofek.superheroes.network.modelsdto.WorkDto


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