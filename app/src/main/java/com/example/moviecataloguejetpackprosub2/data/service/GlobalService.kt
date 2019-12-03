package com.example.moviecataloguejetpackprosub2.data.service

import com.example.moviecataloguejetpackprosub2.data.response.TopMoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GlobalService {

    @GET("3/discover/movie")
    fun getMovies(@Query("api_key") api_key: String,
                  @Query("language") language: String,
                  @Query("sort_by") sort_by: String): Single<TopMoviesResponse>

    @GET("3/discover/tv")
    fun getTvShow(@Query("api_key") api_key: String,
                  @Query("language") language: String,
                  @Query("sort_by") sort_by: String)

    @GET("3/movie/{movie_id}")
    fun getDetailMovies(@Path("movie_id") movie_id: String,
                        @Query("api_key") api_key: String,
                        @Query("language") language: String)
    @GET("3/tv/{tv_id}")
    fun getDetailTvShows(@Path("tv_id") tv_id: String,
                         @Query("api_key") api_key: String,
                         @Query("language") language: String)
}