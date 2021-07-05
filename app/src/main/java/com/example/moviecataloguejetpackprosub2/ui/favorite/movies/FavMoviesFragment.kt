package com.example.moviecataloguejetpackprosub2.ui.favorite.movies


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.moviecataloguejetpackprosub2.R
import com.example.moviecataloguejetpackprosub2.helper.Status
import com.example.moviecataloguejetpackprosub2.helper.toast
import kotlinx.android.synthetic.main.fragment_fav_movies.*
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class FavMoviesFragment : Fragment() {

    private val vm:FavMoviesVM by inject()

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
        val movieAdapter = FavMovieAdapter(context)

        vm.getMoviesPage.observe(viewLifecycleOwner, Observer { response ->
            if (response != null) {
                when (response.status) {
                    Status.LOADING -> {

                    }
                    Status.SUCCESS -> {
                        progressBarHolderLoginCL.visibility = View.GONE
                        movieAdapter.submitList(response.data)
                        movieAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        progressBarHolderLoginCL.visibility = View.GONE
                        toast( context!!,getString(R.string.error))
                    }
                }
            }
        })


        favMoviesRV.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = movieAdapter
        }

    }
}
