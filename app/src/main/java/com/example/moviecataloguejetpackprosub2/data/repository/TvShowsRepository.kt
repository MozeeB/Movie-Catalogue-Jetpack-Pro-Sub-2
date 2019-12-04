package com.example.moviecataloguejetpackprosub2.data.repository

import com.example.moviecataloguejetpackprosub2.domain.TvShowDomain
import io.reactivex.Single

interface TvShowsRepository {
    fun getTvShows(apiKey:String, language:String, shortBy:String) : Single<List<TvShowDomain>>
}