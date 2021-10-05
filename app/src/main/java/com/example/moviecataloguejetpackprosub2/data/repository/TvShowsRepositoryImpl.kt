package com.example.moviecataloguejetpackprosub2.data.repository

import com.example.moviecataloguejetpackprosub2.data.mapper.TvShowsMapper
import com.example.moviecataloguejetpackprosub2.data.service.GlobalService
import com.example.moviecataloguejetpackprosub2.domain.TvShowDomain
import io.reactivex.Single

class TvShowsRepositoryImpl(
    private val service: GlobalService,
    private val tvShowsMapper: TvShowsMapper
) : TvShowsRepository{
    override fun getTvShows(
        apiKey: String,
        language: String,
        shortBy: String
    ): Single<List<TvShowDomain>> {
        return service.getTvShow(apiKey, language, shortBy).map {
            tvShowsMapper.mapToListDomain(it.results)
        }
    }
}