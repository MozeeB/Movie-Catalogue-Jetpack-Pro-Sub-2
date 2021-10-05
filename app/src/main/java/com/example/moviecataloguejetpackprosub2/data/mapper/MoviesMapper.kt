package com.example.moviecataloguejetpackprosub2.data.mapper

import com.example.moviecataloguejetpackprosub2.data.model.MoviesModel
import com.example.moviecataloguejetpackprosub2.domain.MoviesDomain

class MoviesMapper : BaseMapper<MoviesModel, MoviesDomain> {
    override fun mapToDomain(model: MoviesModel): MoviesDomain {
        return MoviesDomain(
            model.poster_path,
            model.id,
            model.backdrop_path,
            model.original_title,
            model.vote_average,
            model.overview,
            model.release_date
        )
    }

    override fun mapToModel(domain: MoviesDomain): MoviesModel {
        return MoviesModel(
            domain.poster_path,
            domain.id,
            domain.backdrop_path,
            domain.original_title,
            domain.vote_average,
            domain.overview,
            domain.release_date
        )
    }
}