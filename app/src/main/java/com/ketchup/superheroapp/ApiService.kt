package com.ketchup.superheroapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    /**
     * Get superheroes from API
     */
    @GET("10161848795970752/search/{name}")
    suspend fun getSuperheroes(@Path("name") superheroName: String): Response<SuperHeroDataResponse>

    /**
     * Get superhero detail from API
     */
    @GET("10161848795970752/{id}")
    suspend fun getSuperheroDetail(@Path("id") superheroId: String): Response<SuperHeroDetailResponse>
}