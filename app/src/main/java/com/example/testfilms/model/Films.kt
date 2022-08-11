package com.example.testfilms.model

data class Films(
    var items: List<Items>
)

data class Items(
    val title: String,
    val directorName: String,
    val releaseYear: Int,
    val actors: List<Actors>
)

data class Actors(val actorName: String)
