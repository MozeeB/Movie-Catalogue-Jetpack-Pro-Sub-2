package com.example.moviecataloguejetpackprosub2.data.mapper

import com.example.moviecataloguejetpackprosub2.data.model.TvShowModel
import com.example.moviecataloguejetpackprosub2.domain.TvShowDomain

class TvShowsMapper : BaseMapper<TvShowModel, TvShowDomain> {
    override fun mapToDomain(model: TvShowModel): TvShowDomain {
        return TvShowDomain(
            model.original_name,
            model.first_air_date,
            model.backdrop_path,
            model.id,
            model.vote_average,
            model.overview,
            model.poster_path
        )
    }

    override fun mapToModel(domain: TvShowDomain): TvShowModel {
        return TvShowModel(
            domain.original_name,
            domain.first_air_date,
            domain.backdrop_path,
            domain.id,
            domain.vote_average,
            domain.overview,
            domain.poster_path
        )
    }
}