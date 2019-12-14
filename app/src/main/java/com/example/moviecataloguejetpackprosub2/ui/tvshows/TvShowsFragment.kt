package com.example.moviecataloguejetpackprosub2.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecataloguejetpackprosub2.R
import com.example.moviecataloguejetpackprosub2.helper.PaginationScrollListener
import com.example.moviecataloguejetpackprosub2.helper.toast
import com.example.moviecataloguejetpackprosub2.helper.view.LoadmoreItemView
import com.example.moviecataloguejetpackprosub2.helper.view.TvShowsItemView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_tvshows.*
import org.koin.android.ext.android.inject

class TvShowsFragment : Fragment() {

    private val vm:TvShowsViewModel by inject()

    private val adapterTvShows = GroupAdapter<ViewHolder>()

    private var page = 1
    private var isLoadMore = false
    private var isLastPage = false

    private var loadmoreItemView = LoadmoreItemView()


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
        vm.getTvShows(page)


        setupRV()
    }


    private fun setupRV(){
        val linearLayout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        tvshowsFragmentRV.apply {
            layoutManager = linearLayout
            adapter = adapterTvShows
        }

        tvshowsFragmentRV.addOnScrollListener(object : PaginationScrollListener(linearLayout){
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoadMore
            }

            override fun loadMoreItems() {
                isLoadMore = true
                page++

                vm.getTvShows(page)
            }

        })
    }


    private val startObserver = Observer<TvShowsState>{ tvShowsState ->
        when(tvShowsState){
            is TvShowDataLoaded ->{
                progressBarHolderLoginCL.visibility = View.GONE

                if (isLoadMore) {
                    // remove loading indicator
                    adapterTvShows.remove(loadmoreItemView)
                    isLoadMore = false
                }
                if (page == 1) {
                    adapterTvShows.clear()
                }
                tvShowsState.tvShowDomain.map {
                    adapterTvShows.add(TvShowsItemView(it))

                }
            }
            is ErrorState ->{
                progressBarHolderLoginCL.visibility = View.GONE

            }

            is LoadingState ->{
                if (isLoadMore) {
                    // add loading indicator
                    adapterTvShows.add(loadmoreItemView)
                }
            }
            is LastPageState ->{
                if (isLoadMore) {
                    // remove loading indicator
                    adapterTvShows.remove(loadmoreItemView)
                    isLoadMore = false
                    if (!isLastPage) {
                        toast(
                            this.context!!,
                            "Telah meraih halaman terakhir"
                        )
                        isLastPage = true
                    }
                }
            }
            is DataNotFoundState ->{
                adapterTvShows.clear()
            }
        }
    }
}