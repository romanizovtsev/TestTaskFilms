package com.example.testfilms.model

import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET("films.json")
    suspend fun getFilms(): Response<Films>
}