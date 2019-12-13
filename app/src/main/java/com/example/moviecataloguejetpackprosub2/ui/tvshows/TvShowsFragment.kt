package com.example.moviecataloguejetpackprosub2.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecataloguejetpackprosub2.R
import com.example.moviecataloguejetpackprosub2.helper.view.TvShowsItemView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_tvshows.*
import org.koin.android.ext.android.inject

class TvShowsFragment : Fragment() {

    private val vm:TvShowsViewModel by inject()

    private val adapterTvShows = GroupAdapter<ViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tvshows, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        progressBarHolderLoginCL.visibility = View.VISIBLE
        vm.tvShowsState.observe(this, startObserver)
        vm.getTvShows()

        tvshowsFragmentRV.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterTvShows
        }
    }


    private val startObserver = Observer<TvShowsState>{ tvShowsState ->
        when(tvShowsState){
            is TvShowDataLoaded ->{
                adapterTvShows.clear()
                tvShowsState.tvShowDomain.map {
                    adapterTvShows.add(TvShowsItemView(it))
                    progressBarHolderLoginCL.visibility = View.GONE

                }
            }
            is ErrorState ->{
                progressBarHolderLoginCL.visibility = View.GONE

            }
        }
    }
}