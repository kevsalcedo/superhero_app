package com.ketchup.superheroapp

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("10161848795970752/search/name")
    suspend fun getSuperheroes(@Path("name") superheroName: String)

}