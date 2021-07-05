package com.example.moviecataloguejetpackprosub2.ui.favorite.tvshows


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.moviecataloguejetpackprosub2.R
import com.example.moviecataloguejetpackprosub2.helper.Status
import com.example.moviecataloguejetpackprosub2.helper.toast
import kotlinx.android.synthetic.main.fragment_fav_tv_shows.*
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class FavTvShowsFragment : Fragment() {

    private val vm:FavTvShowsVM by inject()

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
        val tvshowAdapter = FavTvShowsAdapter(context)


        vm.getTvShowsPage.observe(viewLifecycleOwner, Observer { response ->
            if (response != null) {
                when (response.status) {
                    Status.LOADING -> {

                    }
                    Status.SUCCESS -> {
                        progressBarHolderLoginCL.visibility = View.GONE
                        tvshowAdapter.submitList(response.data)
                        tvshowAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        progressBarHolderLoginCL.visibility = View.GONE
                        toast( context!!,getString(R.string.error))
                    }
                }
            }
        })


        favTvshowsRV.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = tvshowAdapter
        }

    }


}
