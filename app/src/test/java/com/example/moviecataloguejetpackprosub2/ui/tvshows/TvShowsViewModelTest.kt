package com.example.moviecataloguejetpackprosub2.ui.tvshows

import com.example.moviecataloguejetpackprosub2.BuildConfig
import com.example.moviecataloguejetpackprosub2.data.repository.TvShowsRepository
import com.example.moviecataloguejetpackprosub2.domain.TvShowDomain
import com.example.moviecataloguejetpackprosub2.helper.MOVIE
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TvShowsViewModelTest{
    private lateinit var viewModel: TvShowsViewModel

    @Mock
    private lateinit var response: List<TvShowDomain>

    @Mock
    private lateinit var repository: TvShowsRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = TvShowsViewModel(repository)
    }

    @Test
    fun getTvShows(){
        val moviesRespone = response
        runBlocking {
            Mockito.`when`(repository.getTvShows(BuildConfig.API_KEY, MOVIE.LANG, MOVIE.SORT_BY))
        }
            .thenReturn(Single.just(moviesRespone))
        viewModel.getTvShows()


    }
}