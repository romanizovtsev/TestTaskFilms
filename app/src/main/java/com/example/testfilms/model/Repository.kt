package com.example.testfilms.model

class Repository constructor(private val retrofitService: RetrofitService) {

    fun getFilms() = retrofitService.getFilms()

}