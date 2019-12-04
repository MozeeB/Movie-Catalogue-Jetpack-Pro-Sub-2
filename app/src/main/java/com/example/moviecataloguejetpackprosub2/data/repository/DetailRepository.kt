package com.example.moviecataloguejetpackprosub2.data.repository

import com.example.moviecataloguejetpackprosub2.domain.DetailMovieDomain
import com.example.moviecataloguejetpackprosub2.domain.DetailTvShowDomain
import io.reactivex.Single

interface DetailRepository {

    fun getDetailMovies(apiKey:String, language:String, shortBy:String) : Single<DetailMovieDomain>
    fun getDetailTvShows(apiKey:String, language:String, shortBy:String) : Single<DetailTvShowDomain>

}