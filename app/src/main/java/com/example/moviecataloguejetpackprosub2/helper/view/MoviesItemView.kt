package com.example.moviecataloguejetpackprosub2.helper.view

import com.example.moviecataloguejetpackprosub2.R
import com.example.moviecataloguejetpackprosub2.domain.MoviesDomain
import com.example.moviecataloguejetpackprosub2.helper.MOVIE
import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesItemView(val moviesDomain: MoviesDomain) : Item(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        val image = viewHolder.itemView.movie_poster
        val title = viewHolder.itemView.movie_name
        val overview = viewHolder.itemView.movie_desc
        val date = viewHolder.itemView.movie_date

        Picasso.get().load(MOVIE.LINK_IMAGE + moviesDomain.poster_path).into(image)
        title.text = moviesDomain.original_title
        overview.text = moviesDomain.overview
        date.text = moviesDomain.release_date
    }

    override fun getLayout(): Int = R.layout.item_movie

}