package com.ketchup.superheroapp

import com.google.gson.annotations.SerializedName

data class SuperHeroDetailResponse(
    @SerializedName("id") val superheroId: String,
    @SerializedName("name") val superheroName: String,
    @SerializedName("powerstats") val powerStats: SuperheroPowerStatsResponse,
    @SerializedName("image") val superheroImage: SuperHeroImageDetailResponse
)

data class SuperheroPowerStatsResponse(
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("combat") val combat: String,
)

data class SuperHeroImageDetailResponse(
    @SerializedName("url") val superheroImageDetail: String
)