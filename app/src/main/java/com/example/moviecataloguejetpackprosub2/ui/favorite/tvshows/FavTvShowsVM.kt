package com.example.moviecataloguejetpackprosub2.ui.favorite.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecataloguejetpackprosub2.data.repository.TvShowsRepositoryImpl
import com.example.moviecataloguejetpackprosub2.domain.DetailTvShowDomain
import com.example.moviecataloguejetpackprosub2.helper.Resource


class FavTvShowsVM (tvShowsRepositoryImpl: TvShowsRepositoryImpl) : ViewModel(){

    val getTvShowsPage: LiveData<Resource<PagedList<DetailTvShowDomain>>> = tvShowsRepositoryImpl.getMoviesAsPage()

}