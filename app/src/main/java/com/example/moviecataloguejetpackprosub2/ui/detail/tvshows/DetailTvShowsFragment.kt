package com.example.moviecataloguejetpackprosub2.ui.detail.tvshows


import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer

import com.example.moviecataloguejetpackprosub2.R
import com.example.moviecataloguejetpackprosub2.domain.DetailTvShowDomain
import com.example.moviecataloguejetpackprosub2.helper.MOVIE
import com.example.moviecataloguejetpackprosub2.helper.toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_tv_shows.*
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
class DetailTvShowsFragment : Fragment(), View.OnClickListener {

    private val vm:DetailTvShowsVM by inject()

    var dataTvShow: DetailTvShowDomain? = null

    private var idTvShow: Any? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_tv_shows, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        progressBarHolderLoginCL.visibility = View.VISIBLE

        vm.detailtvShowsState.observe(this, startObserver)

        idTvShow = this.activity?.intent?.extras?.get("idTvShow")

        vm.checkFavTvShows(idTvShow.toString().toInt())

        if (idTvShow != null){
            vm.getDetailTvShows(idTvShow.toString())
        }

        favLoveTvShowsFragmentDetailIB.setOnClickListener(this)

    }

    private val startObserver = Observer<DetailtvShowsState>{ dataState ->
        when(dataState){
            is DetailTvShowDataLoaded ->{
                dataTvShow = dataState.detailTvShowDomain
                val dataTvshows = dataState.detailTvShowDomain

                Picasso.get().load(MOVIE.LINK_IMAGE + dataTvshows.backdrop_path)
                    .into(backMovieDetailFragmentIV)
                Picasso.get().load(MOVIE.LINK_IMAGE + dataTvshows.poster_path)
                    .into(posterDetailFragmentIV)

                dataDescriptionDetailFragmentTV.text = dataTvshows.overview
                titleMovieDetailFragmentTV.text = dataTvshows.original_name
                dateMovieDetailFragmentTV.text = dataTvshows.first_air_date
                voteDetailFragmentTV.text = dataTvshows.vote_average.toString()

                progressBarHolderLoginCL.visibility = View.GONE

            }
            is ErrorState -> {
                progressBarHolderLoginCL.visibility = View.GONE

            }
            is FavTvShowSaved -> {
                favLoveTvShowsFragmentDetailIB.background = ContextCompat.getDrawable(
                    this@DetailTvShowsFragment.context!!,
                    R.drawable.ic_heart_2
                )
                toast(context!!, "add to favorite")
                progressBarHolderLoginCL.visibility = View.GONE
            }
            is RemoveTvShowFav -> {
                favLoveTvShowsFragmentDetailIB.background = ContextCompat.getDrawable(
                    this@DetailTvShowsFragment.context!!,
                    R.drawable.ic_heart_1
                )
                toast(context!!, "remove to favorite")

                progressBarHolderLoginCL.visibility = View.GONE

            }
            is FavTvShowsDataFound -> {
                dataState.detailTvShowDomain.map {
                    if (it.id == idTvShow) {
                        favLoveTvShowsFragmentDetailIB.background = ContextCompat.getDrawable(
                            this@DetailTvShowsFragment.context!!,
                            R.drawable.ic_heart_2
                        )
                    }
                }

            }
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.favLoveTvShowsFragmentDetailIB ->{
                if (favLoveTvShowsFragmentDetailIB.background.constantState == ContextCompat.getDrawable(
                        this.context!!,
                        R.drawable.ic_heart_1
                    )!!.constantState
                ) {
                    vm.saveFavTvShow(dataTvShow!!)
                } else {
                    vm.removeTvShowFav(dataTvShow!!.id)
                }
            }
        }
    }

}
