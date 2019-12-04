package com.example.moviecataloguejetpackprosub2.data.mapper

import com.example.moviecataloguejetpackprosub2.data.model.DetaiMovielModel
import com.example.moviecataloguejetpackprosub2.domain.DetailMovieDomain

class DetailMovieMapper : BaseMapper<DetaiMovielModel, DetailMovieDomain> {
    override fun mapToDomain(model: DetaiMovielModel): DetailMovieDomain {
        return DetailMovieDomain(
            model.backdrop_path,
            model.original_title,
            model.overview,
            model.poster_path,
            model.release_date,
            model.vote_average
        )
    }

    override fun mapToModel(domain: DetailMovieDomain): DetaiMovielModel {
        return DetaiMovielModel(
            domain.backdrop_path,
            domain.original_title,
            domain.overview,
            domain.poster_path,
            domain.release_date,
            domain.vote_average

        )
    }
}