package com.example.moviecataloguejetpackprosub2.helper.view

import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.moviecataloguejetpackprosub2.R
import com.example.moviecataloguejetpackprosub2.domain.DetailTvShowDomain
import com.example.moviecataloguejetpackprosub2.helper.MOVIE
import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_movie.view.*

class FavTvShowsItemView (val detailTvShowDomain: DetailTvShowDomain) : Item(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        val image = viewHolder.itemView.movie_poster
        val title = viewHolder.itemView.movie_name
        val overview = viewHolder.itemView.movie_desc
        val date = viewHolder.itemView.movie_date

        Picasso.get().load(MOVIE.LINK_IMAGE + detailTvShowDomain.poster_path).into(image)
        title.text = detailTvShowDomain.original_name
        overview.text = detailTvShowDomain.overview
        date.text = detailTvShowDomain.first_air_date

        viewHolder.itemView.setOnClickListener {
            val bundle = bundleOf("idTvShow" to detailTvShowDomain.id)
            it.findNavController().navigate(R.id.action_navigation_favorite_to_detailTvShowsActivity, bundle)
        }
    }

    override fun getLayout(): Int = R.layout.item_movie

}