package com.example.reminder

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface HA_API {
    @POST("services/switch/turn_{action}")
    fun toggleLight(
        @Header("Authorization") token: String,
        @Path("action") action: String,
        @Body request: LightRequest
    ): Call<Void>

    @POST("services/Climate/turn_{action}")
    fun toggleClimate(
        @Header("Authorization") token: String,
        @Path("action") action: String,
        @Body request: LightRequest
    ): Call<Void>
}