package com.example.moviecataloguejetpackprosub2.ui.favorite.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecataloguejetpackprosub2.data.repository.MoviesRepositoryImpl
import com.example.moviecataloguejetpackprosub2.domain.DetailMovieDomain
import com.example.moviecataloguejetpackprosub2.helper.Resource


class FavMoviesVM(moviesRepositoryImpl: MoviesRepositoryImpl) : ViewModel() {

    val getMoviesPage: LiveData<Resource<PagedList<DetailMovieDomain>>> = moviesRepositoryImpl.getMoviesAsPage()

}