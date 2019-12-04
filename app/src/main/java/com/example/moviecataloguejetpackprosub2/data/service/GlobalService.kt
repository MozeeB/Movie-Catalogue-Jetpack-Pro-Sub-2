package com.example.moviecataloguejetpackprosub2.data.service

import com.example.moviecataloguejetpackprosub2.data.model.DetaiMovielModel
import com.example.moviecataloguejetpackprosub2.data.model.DetailTvShowModel
import com.example.moviecataloguejetpackprosub2.data.response.TopMoviesResponse
import com.example.moviecataloguejetpackprosub2.data.response.TopTvShowResponse
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
                  @Query("sort_by") sort_by: String) : Single<TopTvShowResponse>

    @GET("3/movie/{movie_id}")
    fun getDetailMovies(@Path("movie_id") movie_id: String,
                        @Query("api_key") api_key: String,
                        @Query("language") language: String) :Single<DetaiMovielModel>

    @GET("3/tv/{id}")
    fun getDetailTvShows(@Path("id") tv_id: String,
                         @Query("api_key") api_key: String,
                         @Query("language") language: String) :Single<DetailTvShowModel>
}