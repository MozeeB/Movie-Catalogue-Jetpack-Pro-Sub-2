package com.example.moviecataloguejetpackprosub2.data.repository


import com.example.moviecataloguejetpackprosub2.domain.MoviesDomain
import io.reactivex.Single

interface MoviesRepository {
    fun getMovies(apiKey:String, language:String, shortBy:String, page:Int) : Single<List<MoviesDomain>>
}