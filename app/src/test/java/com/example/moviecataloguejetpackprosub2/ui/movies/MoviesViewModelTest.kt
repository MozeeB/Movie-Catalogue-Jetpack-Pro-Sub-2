package com.example.moviecataloguejetpackprosub2.ui.movies

import androidx.lifecycle.MutableLiveData
import com.example.moviecataloguejetpackprosub2.BuildConfig
import com.example.moviecataloguejetpackprosub2.data.repository.MoviesRepository
import com.example.moviecataloguejetpackprosub2.data.response.TopMoviesResponse
import com.example.moviecataloguejetpackprosub2.data.service.GlobalService
import com.example.moviecataloguejetpackprosub2.domain.MoviesDomain
import com.example.moviecataloguejetpackprosub2.helper.MOVIE
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @Mock
    private lateinit var response: List<MoviesDomain>

    @Mock
    private lateinit var repository: MoviesRepository


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MoviesViewModel(repository)
    }

    @Test
    fun getMovies(){
        val moviesRespone = response
        runBlocking {
            Mockito.`when`(repository.getMovies(BuildConfig.API_KEY, MOVIE.LANG, MOVIE.SORT_BY))
        }
            .thenReturn(Single.just(moviesRespone))
        viewModel.getMovies()


    }
}