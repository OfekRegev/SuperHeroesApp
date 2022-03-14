package com.ofek.superheroes.network

import com.ofek.superheroes.network.modelsdto.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroesService {

    @GET("search/{name}")
    fun searchSuperHeroesByName(@Path("name") name: String): Single<SearchByNameResponseDto>

    @GET("{id}")
    fun getSuperheroById(@Path("id") id: Int): Single<SuperHeroDto>

    @GET("{id}/connections")
    fun getConnections(@Path("id") id: Int): Single<ConnectionsDto>
    @GET("{id}/work")
    fun getWork(@Path("id") id: Int): Single<WorkDto>
    @GET("{id}/appearance")
    fun getAppearance(@Path("id") id: Int): Single<AppearanceDto>

}