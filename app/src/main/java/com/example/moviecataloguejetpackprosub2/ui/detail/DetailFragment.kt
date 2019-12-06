package com.example.moviecataloguejetpackprosub2.ui.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.moviecataloguejetpackprosub2.BuildConfig

import com.example.moviecataloguejetpackprosub2.R
import com.example.moviecataloguejetpackprosub2.helper.MOVIE
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    private val vm:DetailVM by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vm.detailState.observe(this, startObserver)

        val idMovie = this.activity?.intent?.extras?.get("idMovie")
        val idTvShow = this.activity?.intent?.extras?.get("idTvShow")

        if (idMovie != null){
            vm.getDetailMovies(idMovie.toString())
        }else if (idTvShow != null){
            vm.getDetailTvShows(idTvShow.toString())

        }


    }

    private val startObserver = Observer<DetailState>{ dataState->
        when(dataState){
            is DetailMoviesDataLoaded ->{
                val dataMovie = dataState.detailMovieDomain
                Picasso.get().load(MOVIE.LINK_IMAGE + dataMovie.backdrop_path).into(backMovieDetailFragmentIV)
                Picasso.get().load(MOVIE.LINK_IMAGE + dataMovie.poster_path).into(posterDetailFragmentIV)

                dataDescriptionDetailFragmentTV.text = dataMovie.overview
                titleMovieDetailFragmentTV.text = dataMovie.original_title
                dateMovieDetailFragmentTV.text = dataMovie.release_date
                voteDetailFragmentTV.text = dataMovie.vote_average.toString()

            }
            is DetailTvShowDataLoaded ->{
                val dataTvshows = dataState.detailTvShowDomain

                Picasso.get().load(MOVIE.LINK_IMAGE + dataTvshows.backdrop_path).into(backMovieDetailFragmentIV)
                Picasso.get().load(MOVIE.LINK_IMAGE + dataTvshows.poster_path).into(posterDetailFragmentIV)

                dataDescriptionDetailFragmentTV.text = dataTvshows.overview
                titleMovieDetailFragmentTV.text = dataTvshows.original_name
                dateMovieDetailFragmentTV.text = dataTvshows.first_air_date
                voteDetailFragmentTV.text = dataTvshows.vote_average.toString()
            }
            is ErrorState ->{

            }
        }
    }
}
