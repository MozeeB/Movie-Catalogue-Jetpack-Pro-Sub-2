package com.example.moviecataloguejetpackprosub2.ui.movies

import com.example.moviecataloguejetpackprosub2.BuildConfig
import com.example.moviecataloguejetpackprosub2.data.mapper.MoviesMapper
import com.example.moviecataloguejetpackprosub2.data.model.MoviesModel
import com.example.moviecataloguejetpackprosub2.data.repository.MoviesRepositoryImpl
import com.example.moviecataloguejetpackprosub2.data.response.TopMoviesResponse
import com.example.moviecataloguejetpackprosub2.data.service.GlobalService
import com.example.moviecataloguejetpackprosub2.helper.MOVIE
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeUnit


class MoviesRepositoryTest {

    private val globalService = Mockito.mock(GlobalService::class.java)
    private val moviesMapper = Mockito.mock(MoviesMapper::class.java)

    private lateinit var repositoryImpl: MoviesRepositoryImpl


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repositoryImpl = MoviesRepositoryImpl(globalService, moviesMapper)
    }

    @Test
    fun getMovies() {
        Mockito.`when`(globalService.getMovies(BuildConfig.API_KEY, MOVIE.LANG, MOVIE.SORT_BY))
            .thenReturn(
                Single.just(
                    TopMoviesResponse(
                        listOf(
                            MoviesModel(
                                "qdfARIhgpgZOBh3vfNhWS4hmSo3.jpg",
                                330457,
                                "/xJWPZIYOEFIjZpBL7SVBGnzRYXp.jpg",
                                "Frozen II",
                                7.1,
                                "Elsa, Anna, Kristoff and Olaf head far into the forest to learn the truth about an ancient mystery of their kingdom.",
                                "2019-11-20"
                            )
                        )
                    )
                )
            )
        val expectation =
            listOf(
                MoviesModel(
                    "/mbm8k3GFhXS0ROd9AD1gqYbIFbM.jpg",
                    398978,
                    "/iLLDiO4dbUfFEnRug8DuvFEl1NB.jpg",
                    "The Irishman",
                    8.0,
                    "Pennsylvania, 1956. Frank Sheeran, a war veteran of Irish origin who works as a truck driver, accidentally meets mobster Russell Bufalino. Once Frank becomes his trusted man, Bufalino sends him to Chicago with the task of helping Jimmy Hoffa, a powerful union leader related to organized crime, with whom Frank will maintain a close friendship for nearly twenty years.",
                    "2019-11-01"
                )
            )
        repositoryImpl.getMovies(BuildConfig.API_KEY, MOVIE.LANG, MOVIE.SORT_BY)
            .test()
            .assertComplete()
            .awaitDone(10, TimeUnit.SECONDS)
            .assertValue {
                it != expectation
            }

    }

}
