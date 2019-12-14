package com.example.moviecataloguejetpackprosub2.ui.tvshows

import com.example.moviecataloguejetpackprosub2.BuildConfig
import com.example.moviecataloguejetpackprosub2.data.mapper.TvShowsMapper
import com.example.moviecataloguejetpackprosub2.data.model.TvShowModel
import com.example.moviecataloguejetpackprosub2.data.repository.TvShowsRepositoryImpl
import com.example.moviecataloguejetpackprosub2.data.response.TopTvShowResponse
import com.example.moviecataloguejetpackprosub2.data.service.GlobalService
import com.example.moviecataloguejetpackprosub2.helper.MOVIE
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeUnit

class TvShowsViewModelTest{

    private val globalService = Mockito.mock(GlobalService::class.java)
    private val tvShowsMapper = Mockito.mock(TvShowsMapper::class.java)

    private lateinit var repositoryImpl: TvShowsRepositoryImpl
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repositoryImpl = TvShowsRepositoryImpl(globalService, tvShowsMapper)
    }

    @Test
    fun getTvShows(){
        Mockito.`when`(globalService.getTvShow(BuildConfig.API_KEY, MOVIE.LANG, MOVIE.SORT_BY, 1))
            .thenReturn(
                Single.just(
                    TopTvShowResponse(
                        listOf(
                            TvShowModel(
                                "The Mandalorian",
                                "2019-11-12",
                                "/o7qi2v4uWQ8bZ1tW3KI0Ztn2epk.jpg",
                                82856,
                                7.6,
                                "Set after the fall of the Empire and before the emergence of the First Order, we follow the travails of a lone gunfighter in the outer reaches of the galaxy far from the authority of the New Republic.",
                                "/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg"
                            )
                        )
                    )
                )
            )
        val expectation =
            listOf(
                TvShowModel(
                    "Rick and Morty",
                    "2013-12-02",
                    "/mzzHr6g1yvZ05Mc7hNj3tUdy2bM.jpg",
                    60625,
                    8.6,
                    "Rick is a mentally-unbalanced but scientifically-gifted old man who has recently reconnected with his family. He spends most of his time involving his young grandson Morty in dangerous, outlandish adventures throughout space and alternate universes. Compounded with Morty's already unstable family life, these events cause Morty much distress at home and school.",
                    "/qJdfO3ahgAMf2rcmhoqngjBBZW1.jpg"
                )
            )
        repositoryImpl.getTvShows(BuildConfig.API_KEY, MOVIE.LANG, MOVIE.SORT_BY, 1)
            .test()
            .assertComplete()
            .awaitDone(10, TimeUnit.SECONDS)
            .assertValue {
                it != expectation
            }

    }
}