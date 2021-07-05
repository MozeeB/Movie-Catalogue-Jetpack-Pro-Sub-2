package com.example.moviecataloguejetpackprosub2.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_tvshow")
class DetailTvShowDomain(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id:Int,
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path:String,
    @ColumnInfo(name = "first_air_date")
    val first_air_date:String,
    @ColumnInfo(name = "original_name")
    val original_name:String,
    @ColumnInfo(name = "overview")
    val overview:String,
    @ColumnInfo(name = "poster_path")
    val poster_path:String,
    @ColumnInfo(name = "vote_average")
    val vote_average:Double
)