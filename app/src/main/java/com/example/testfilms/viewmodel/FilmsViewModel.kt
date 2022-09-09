package com.example.testfilms.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testfilms.model.Items
import com.example.testfilms.model.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject


class FilmsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val filmsLiveData: LiveData<List<Items>>
        get() = repository.films

    init {
        viewModelScope.launch {
            repository.getFilms()
        }
    }

}


