package com.smallrents

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("Usuario/Inserir")
    fun userRegistration(@Body request: RegistrationRequest): Call<RegistrationResponse>
}