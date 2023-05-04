package com.ketchup.superheroapp

import com.google.gson.annotations.SerializedName


/**
 * Data class to map the response from the API
 */
data class SuperHeroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val superheroList: List<SuperheroItemResponse>
)

data class SuperheroItemResponse(
    @SerializedName("id") val superheroId: String,
    @SerializedName("name") val superheroName: String,
    @SerializedName("image") val superheroImage: SuperheroImageResponse
)

data class SuperheroImageResponse(
    @SerializedName("url") val url: String
)