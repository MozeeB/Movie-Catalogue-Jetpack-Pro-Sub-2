package com.example.moviecataloguejetpackprosub2.ui.detail

import com.example.moviecataloguejetpackprosub2.BuildConfig
import com.example.moviecataloguejetpackprosub2.data.repository.DetailRepository
import com.example.moviecataloguejetpackprosub2.domain.DetailMovieDomain
import com.example.moviecataloguejetpackprosub2.domain.DetailTvShowDomain
import com.example.moviecataloguejetpackprosub2.helper.MOVIE
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailVMTest{

    private lateinit var viewModel: DetailVM

    @Mock
    private lateinit var response: DetailMovieDomain

    @Mock
    private lateinit var detailTvShowDomain: DetailTvShowDomain

    @Mock
    private lateinit var repository: DetailRepository


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailVM(repository)
    }

    @Test
    fun getDetailMovies(){
        val moviesRespone = response
        runBlocking {
            Mockito.`when`(repository.getDetailMovies("330457", BuildConfig.API_KEY, MOVIE.LANG))
        }
            .thenReturn(Single.just(moviesRespone))
        viewModel.getDetailMovies("330457")

    }

    @Test
    fun getDetailTvShows(){
        val tvShowDomain = detailTvShowDomain
        runBlocking {
            Mockito.`when`(repository.getDetailTvShows("60735", BuildConfig.API_KEY, MOVIE.LANG))
        }
            .thenReturn(Single.just(tvShowDomain))
        viewModel.getDetailTvShows("60735")


    }
}