package com.example.moviecataloguejetpackprosub2.data.model

data class DetaiMovielModel(
    val id: Int,
    val backdrop_path: String,
    val original_title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val vote_average: Double
)