package com.ofek.superheroes.herodetailsscreen.network

import com.ofek.superheroes.network.SuperHeroesService
import com.ofek.superheroes.network.modelsdto.AppearanceDto
import com.ofek.superheroes.network.modelsdto.ConnectionsDto
import com.ofek.superheroes.network.modelsdto.SuperHeroDto
import com.ofek.superheroes.network.modelsdto.WorkDto
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class HeroDetailsRepository @Inject constructor(
    val service: SuperHeroesService
) {
    fun getConnections(heroId: Int): Single<ConnectionsDto> {
        return service.getConnections(heroId)
    }

    fun getWork(heroId: Int): Single<WorkDto> {
        return service.getWork(heroId)
    }

    fun getAppearance(heroId: Int): Single<AppearanceDto> {
        return service.getAppearance(heroId)
    }


}