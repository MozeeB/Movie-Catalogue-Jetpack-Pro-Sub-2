package com.example.moviecataloguejetpackprosub2.helper

import android.content.Context
import android.widget.Toast

object MOVIE {
    val BASE_URL = "https://api.themoviedb.org/"
    val LINK_IMAGE = "http://image.tmdb.org/t/p/w185/"
    val LANG = "en-US"
    val SORT_BY = "popularity.desc"
}
object LOCAL{
    val NAME_DATABASE = "db_movie"
}

fun toast(v : Context, message:String){
    Toast.makeText(
        v,
        message,
        Toast.LENGTH_SHORT
    ).show()
}