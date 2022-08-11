package com.example.testfilms.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testfilms.model.Films
import com.example.testfilms.model.Items
import com.example.testfilms.model.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FilmsViewModel(private val repository: Repository) : ViewModel() {


    val movieList = MutableLiveData<List<Items>>()
    val errorMessage = MutableLiveData<String>()

    fun getFilms() {
        val response = repository.getFilms()
        response.enqueue(object : Callback<Films> {
            override fun onResponse(call: Call<Films>, response: Response<Films>) {
                movieList.postValue(response.body()!!.items)
            }

            override fun onFailure(call: Call<Films>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}