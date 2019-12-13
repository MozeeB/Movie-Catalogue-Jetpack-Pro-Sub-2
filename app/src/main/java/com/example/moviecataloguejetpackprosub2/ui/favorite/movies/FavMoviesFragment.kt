package com.example.moviecataloguejetpackprosub2.ui.favorite.movies


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.moviecataloguejetpackprosub2.R
import com.example.moviecataloguejetpackprosub2.helper.view.FavMoviesItemView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_fav_movies.*
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class FavMoviesFragment : Fragment() {

    private val vm:FavMoviesVM by inject()

    private val adapterFavMovies = GroupAdapter<ViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        progressBarHolderLoginCL.visibility = View.VISIBLE
        vm.favMoviesState.observe(this, starObserver)

        if (savedInstanceState == null){
            adapterFavMovies.clear()
            vm.getFavMovies()
        }else{
            vm.getFavMovies()
        }

        favMoviesRV.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterFavMovies
        }

    }

    private val starObserver = Observer<FavMoviesState>{ moviesState ->
        when(moviesState){
            is FavMoviesDataLoaded ->{
                noFoundMoviesTV.visibility = View.GONE
                adapterFavMovies.clear()
                moviesState.movieDomain.map {
                    adapterFavMovies.add(FavMoviesItemView(it))
                }
                progressBarHolderLoginCL.visibility = View.GONE


            }
            is NoDataFound ->{
                progressBarHolderLoginCL.visibility = View.GONE
                noFoundMoviesTV.visibility = View.VISIBLE

            }
            is ErrorState ->{
                progressBarHolderLoginCL.visibility = View.GONE

            }
        }
    }

    override fun onResume() {
        super.onResume()
        vm.getFavMovies()

    }

}
