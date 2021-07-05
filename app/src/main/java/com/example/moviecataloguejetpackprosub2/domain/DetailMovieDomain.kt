package com.example.moviecataloguejetpackprosub2.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_movies")
class DetailMovieDomain(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id:Int,
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path:String,
    @ColumnInfo(name = "original_title")
    val original_title:String,
    @ColumnInfo(name = "overview")
    val overview:String,
    @ColumnInfo(name = "poster_path")
    val poster_path:String,
    @ColumnInfo(name = "release_date")
    val release_date:String,
    @ColumnInfo(name = "vote_average")
    val vote_average:Double
)