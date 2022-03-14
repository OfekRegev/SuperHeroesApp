package com.ofek.superheroes.searchscreen.network

import com.ofek.superheroes.network.SuperHeroesService
import com.ofek.superheroes.network.modelsdto.SuperHeroDto
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

// a repository to allow data manipulations in order to prepare the data for the view model
class SearchScreenRepository @Inject constructor(
    private val superHeroesService: SuperHeroesService
) {

    fun searchSuperheroes(query: String): Single<List<SuperHeroDto>> {
        return superHeroesService.searchSuperHeroesByName(query)
            .map { it.results }
    }

    fun getSuperheroById(id: Int): Single<SuperHeroDto> {
        return superHeroesService.getSuperheroById(id)
    }


}