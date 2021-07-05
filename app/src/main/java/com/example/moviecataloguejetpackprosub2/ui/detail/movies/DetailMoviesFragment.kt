package com.example.moviecataloguejetpackprosub2.ui.detail.movies


import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer

import com.example.moviecataloguejetpackprosub2.R
import com.example.moviecataloguejetpackprosub2.domain.DetailMovieDomain
import com.example.moviecataloguejetpackprosub2.helper.MOVIE
import com.example.moviecataloguejetpackprosub2.helper.toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_movies.*
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class DetailMoviesFragment : Fragment(), View.OnClickListener {

    private val moviesVm: DetailMoviesVM by inject()

    var datamovie: DetailMovieDomain? = null

    var idMovie: Any? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_movies, container, false)
    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        progressBarHolderLoginCL.visibility = View.VISIBLE

        moviesVm.detailState.observe(this, startObserver)

        idMovie = this.activity?.intent?.extras?.get("idMovie")
        moviesVm.checkFavMovie(idMovie.toString().toInt())
        if (idMovie != null) {
            moviesVm.getDetailMovies(idMovie.toString())
        }

        favLoveFragmentDetailIB.setOnClickListener(this)
        backFragmentDetailIV.setOnClickListener(this)


    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private val startObserver = Observer<DetailState> { dataState ->
        when (dataState) {
            is DetailMoviesDataLoaded -> {
                datamovie = dataState.detailMovieDomain
                val dataMovie = dataState.detailMovieDomain
                Picasso.get().load(MOVIE.LINK_IMAGE + dataMovie.backdrop_path)
                    .into(backMovieDetailFragmentIV)
                Picasso.get().load(MOVIE.LINK_IMAGE + dataMovie.poster_path)
                    .into(posterDetailFragmentIV)

                dataDescriptionDetailFragmentTV.text = dataMovie.overview
                titleMovieDetailFragmentTV.text = dataMovie.original_title
                dateMovieDetailFragmentTV.text = dataMovie.release_date
                voteDetailFragmentTV.text = dataMovie.vote_average.toString()

                progressBarHolderLoginCL.visibility = View.GONE


            }
            is ErrorState -> {
                progressBarHolderLoginCL.visibility = View.GONE

            }
            is FavMoviesSaved -> {
                favLoveFragmentDetailIB.background = ContextCompat.getDrawable(
                    this@DetailMoviesFragment.context!!,
                    R.drawable.ic_heart_2
                )
                toast(context!!, "add to favorite")
                progressBarHolderLoginCL.visibility = View.GONE
            }
            is RemoveMovieFav -> {
                favLoveFragmentDetailIB.background = ContextCompat.getDrawable(
                    this@DetailMoviesFragment.context!!,
                    R.drawable.ic_heart_1
                )
                toast(context!!, "remove to favorite")

                progressBarHolderLoginCL.visibility = View.GONE

            }
            is FavMovieDataFound -> {
                dataState.detailMovieDomain.map {
                    if (it.id == idMovie) {
                        favLoveFragmentDetailIB.background = ContextCompat.getDrawable(
                            this@DetailMoviesFragment.context!!,
                            R.drawable.ic_heart_2
                        )
                    }
                }

            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.favLoveFragmentDetailIB -> {
                if (favLoveFragmentDetailIB.background.constantState == ContextCompat.getDrawable(
                        this.context!!,
                        R.drawable.ic_heart_1
                    )!!.constantState
                ) {
                    moviesVm.saveFavMovieSave(datamovie!!)
                } else {
                    moviesVm.removeMovieFav(datamovie!!.id)
                }
            }
            R.id.backFragmentDetailIV ->{
                activity?.onBackPressed()
            }

        }
    }
}
