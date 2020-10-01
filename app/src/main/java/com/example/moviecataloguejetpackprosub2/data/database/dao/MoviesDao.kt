package com.example.moviecataloguejetpackprosub2.data.database.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviecataloguejetpackprosub2.domain.DetailMovieDomain
import io.reactivex.Single

@Dao
interface MoviesDao {
    
    fun pakdeLoveAmmar() {
        
    }
    
    @Query("SELECT * FROM tb_movies")
    fun  getFavMovies() : Single<List<DetailMovieDomain>>

    @Query("SELECT * FROM tb_movies WHERE id=:idMovie")
    fun  getFavMoviesById(idMovie: Int) : Single<List<DetailMovieDomain>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavMovies(movies: DetailMovieDomain)

    @Query("DELETE from tb_movies where id=:idMovie ")
    fun removeMovie(idMovie: Int)

    @Query("SELECT * FROM tb_movies ORDER BY id ASC")
    fun getFavMoviesPagination() : DataSource.Factory<Int, DetailMovieDomain>

}
