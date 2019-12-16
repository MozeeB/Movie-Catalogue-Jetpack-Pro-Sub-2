package com.example.moviecataloguejetpackprosub2.data.database.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviecataloguejetpackprosub2.domain.DetailTvShowDomain
import io.reactivex.Single

@Dao
interface TvShowDao {

    @Query("SELECT * FROM tb_tvshow")
    fun  getFavTvShow() : Single<List<DetailTvShowDomain>>

    @Query("SELECT * FROM tb_tvshow WHERE id=:idTvShows")
    fun  getFavTvShowById(idTvShows: Int) : Single<List<DetailTvShowDomain>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavTvShows(movies: DetailTvShowDomain)

    @Query("DELETE from tb_tvshow where id=:idTvShows ")
    fun removeTvShows(idTvShows: Int)

    @Query("SELECT * FROM tb_tvshow ORDER BY id ASC")
    fun getFavMoviesPagination() : DataSource.Factory<Int, DetailTvShowDomain>
}