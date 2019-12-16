package com.example.moviecataloguejetpackprosub2.ui.favorite.movies

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecataloguejetpackprosub2.R
import com.example.moviecataloguejetpackprosub2.domain.DetailMovieDomain
import com.example.moviecataloguejetpackprosub2.helper.MOVIE
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class FavMovieAdapter(private val context: Context? ) : PagedListAdapter<DetailMovieDomain, FavMovieAdapter.ViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)

        if (movie != null) {
            holder.tvTitle.text = movie.original_title
            Picasso.get().load(MOVIE.LINK_IMAGE + movie.poster_path).into(holder.poster)
            holder.description.text = movie.overview
            holder.date.text = movie.release_date

            holder.itemView.setOnClickListener {
                val bundle = bundleOf("idMovie" to movie.id)
               it.findNavController().navigate(R.id.action_favoriteFragment_to_detailActivity, bundle)
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.movie_poster
        val tvTitle: TextView = itemView.movie_name
        val description :TextView = itemView.movie_desc
        val date : TextView = itemView.movie_date
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DetailMovieDomain>() {
            override fun areItemsTheSame(oldItem: DetailMovieDomain, newItem: DetailMovieDomain): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: DetailMovieDomain, newItem: DetailMovieDomain): Boolean {
                return oldItem == newItem
            }
        }
    }
}



