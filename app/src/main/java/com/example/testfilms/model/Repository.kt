package com.example.testfilms.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class Repository @Inject constructor(private val retrofitService: RetrofitService) {
    private val _films = MutableLiveData<List<Items>>()
    val films: LiveData<List<Items>>
        get() = _films

    suspend fun getFilms() {
        val result = retrofitService.getFilms()
        if (result.isSuccessful && result.body() != null)
            _films.postValue(result.body()!!.items)
    }

}


