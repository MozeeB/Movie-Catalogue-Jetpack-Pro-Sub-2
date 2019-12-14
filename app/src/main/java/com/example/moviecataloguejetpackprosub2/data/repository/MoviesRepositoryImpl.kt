package com.example.moviecataloguejetpackprosub2.data.repository

import com.example.moviecataloguejetpackprosub2.data.mapper.MoviesMapper
import com.example.moviecataloguejetpackprosub2.data.service.GlobalService
import com.example.moviecataloguejetpackprosub2.domain.MoviesDomain
import io.reactivex.Single

class MoviesRepositoryImpl(
    private val service: GlobalService,
    private val moviesMapper: MoviesMapper
) : MoviesRepository{
    override fun getMovies(
        apiKey: String,
        language: String,
        shortBy: String,
        page:Int
    ): Single<List<MoviesDomain>> {
        return service.getMovies(apiKey, language, shortBy, page).map {
            moviesMapper.mapToListDomain(it.results)
        }
    }
}