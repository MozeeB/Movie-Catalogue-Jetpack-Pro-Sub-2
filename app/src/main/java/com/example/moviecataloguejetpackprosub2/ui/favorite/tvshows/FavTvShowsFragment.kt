package com.example.moviecataloguejetpackprosub2.ui.favorite.tvshows


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.moviecataloguejetpackprosub2.R
import com.example.moviecataloguejetpackprosub2.helper.toast
import com.example.moviecataloguejetpackprosub2.helper.view.FavTvShowsItemView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_fav_tv_shows.*
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class FavTvShowsFragment : Fragment() {

    private val vm:FavTvShowsVM by inject()
    private val adapterFavtvShows = GroupAdapter<ViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_tv_shows, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        progressBarHolderLoginCL.visibility = View.VISIBLE
        vm.favMoviesState.observe(this, startObserver)

        if (savedInstanceState == null){
            adapterFavtvShows.clear()
            vm.getFavTvShows()
        }else{
            vm.getFavTvShows()
        }

        favTvshowsRV.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterFavtvShows
        }

    }

    private val startObserver = Observer<FavTvShowsState>{ detailState ->
        when(detailState){
            is FavMoviesDataLoaded ->{
                noFoundTV.visibility = View.GONE
                adapterFavtvShows.clear()
                detailState.movieDomain.map {
                    adapterFavtvShows.add(FavTvShowsItemView(it))
                }

                progressBarHolderLoginCL.visibility = View.GONE

            }
            is NoDataFound ->{
                progressBarHolderLoginCL.visibility = View.GONE
                noFoundTV.visibility = View.VISIBLE
            }
            is ErrorState ->{
                toast(context!!, "Terjadi kesalahan!")
                progressBarHolderLoginCL.visibility = View.VISIBLE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        adapterFavtvShows.clear()
        vm.getFavTvShows()

    }

}
