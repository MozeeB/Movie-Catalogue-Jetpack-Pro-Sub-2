package com.example.moviecataloguejetpackprosub2.data.repository

import com.example.moviecataloguejetpackprosub2.domain.DetailMovieDomain
import com.example.moviecataloguejetpackprosub2.domain.DetailTvShowDomain
import io.reactivex.Single

interface DetailRepository {

    fun getDetailMovies(id:String, language:String, shortBy:String) : Single<DetailMovieDomain>
    fun getDetailTvShows(id:String, language:String, shortBy:String) : Single<DetailTvShowDomain>

}