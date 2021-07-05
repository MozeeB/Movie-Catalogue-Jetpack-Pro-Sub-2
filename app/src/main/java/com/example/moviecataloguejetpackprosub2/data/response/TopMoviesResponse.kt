package com.example.moviecataloguejetpackprosub2.data.response

import com.example.moviecataloguejetpackprosub2.data.model.MoviesModel

data class TopMoviesResponse(
    val results: List<MoviesModel>
)