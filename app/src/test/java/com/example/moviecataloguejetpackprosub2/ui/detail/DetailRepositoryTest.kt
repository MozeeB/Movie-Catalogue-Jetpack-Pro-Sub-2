package com.example.moviecataloguejetpackprosub2.ui.detail

import com.example.moviecataloguejetpackprosub2.BuildConfig
import com.example.moviecataloguejetpackprosub2.data.mapper.DetailMovieMapper
import com.example.moviecataloguejetpackprosub2.data.mapper.DetailTvShowMapper
import com.example.moviecataloguejetpackprosub2.data.model.DetaiMovielModel
import com.example.moviecataloguejetpackprosub2.data.model.DetailTvShowModel
import com.example.moviecataloguejetpackprosub2.data.repository.DetailRepositoryImpl
import com.example.moviecataloguejetpackprosub2.data.service.GlobalService
import com.example.moviecataloguejetpackprosub2.helper.MOVIE
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailRepositoryTest {

    private val globalService = Mockito.mock(GlobalService::class.java)
    private val moviesMapper = Mockito.mock(DetailMovieMapper::class.java)
    private val tvshowmapper = Mockito.mock(DetailTvShowMapper::class.java)

    private lateinit var repositoryImpl: DetailRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repositoryImpl = DetailRepositoryImpl(globalService, moviesMapper, tvshowmapper)
    }

    @Test
    fun getDetailMovies() {
        Mockito.`when`(globalService.getDetailMovies("330457", MOVIE.LANG, MOVIE.SORT_BY))
            .thenReturn(
                Single.just(
                    DetaiMovielModel(
                        "/vZiqhw6oFoMlHSneIdVip9rRou2.jpg",
                        "Frozen II",
                        "Elsa, Anna, Kristoff and Olaf head far into the forest to learn the truth about an ancient mystery of their kingdom.",
                        "/qdfARIhgpgZOBh3vfNhWS4hmSo3.jpg",
                        "2019-11-20",
                        7.1
                    )
                )
            )

        repositoryImpl.getDetailMovies("330457", MOVIE.LANG, MOVIE.SORT_BY)
            .test()
            .assertNotComplete()
    }

    @Test
    fun getDetailTvShows() {
        Mockito.`when`(globalService.getDetailTvShows("82856", MOVIE.LANG, MOVIE.SORT_BY))
            .thenReturn(
                Single.just(
                    DetailTvShowModel(
                        "/o7qi2v4uWQ8bZ1tW3KI0Ztn2epk.jpg",
                        "2019-11-12",
                        "The Mandalorian",
                        "Set after the fall of the Empire and before the emergence of the First Order, we follow the travails of a lone gunfighter in the outer reaches of the galaxy far from the authority of the New Republic.",
                        "/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg",
                        7.7
                    )
                )
            )

        repositoryImpl.getDetailTvShows("82856", MOVIE.LANG, MOVIE.SORT_BY)
            .test()
            .assertNotComplete()
    }
}