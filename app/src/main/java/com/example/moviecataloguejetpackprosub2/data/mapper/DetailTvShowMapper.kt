package com.example.moviecataloguejetpackprosub2.data.mapper

import com.example.moviecataloguejetpackprosub2.data.model.DetailTvShowModel
import com.example.moviecataloguejetpackprosub2.domain.DetailTvShowDomain

class DetailTvShowMapper : BaseMapper<DetailTvShowModel, DetailTvShowDomain> {
    override fun mapToDomain(model: DetailTvShowModel): DetailTvShowDomain {
        return DetailTvShowDomain(
            model.backdrop_path,
            model.first_air_date,
            model.original_name,
            model.overview,
            model.poster_path,
            model.vote_average
        )
    }

    override fun mapToModel(domain: DetailTvShowDomain): DetailTvShowModel {
        return DetailTvShowModel(
            domain.backdrop_path,
            domain.first_air_date,
            domain.original_name,
            domain.overview,
            domain.poster_path,
            domain.vote_average
        )
    }
}