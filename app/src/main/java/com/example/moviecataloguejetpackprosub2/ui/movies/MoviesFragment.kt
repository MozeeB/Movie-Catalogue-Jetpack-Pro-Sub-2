package com.example.moviecataloguejetpackprosub2.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecataloguejetpackprosub2.R
import com.example.moviecataloguejetpackprosub2.helper.PaginationScrollListener
import com.example.moviecataloguejetpackprosub2.helper.toast
import com.example.moviecataloguejetpackprosub2.helper.view.LoadmoreItemView
import com.example.moviecataloguejetpackprosub2.helper.view.MoviesItemView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_movies.*
import org.koin.android.ext.android.inject

class MoviesFragment : Fragment() {

    private val vm:MoviesViewModel by inject()

    private val adapterMovies = GroupAdapter<ViewHolder>()

    private var page = 1
    private var isLoadMore = false
    private var isLastPage = false

    private var loadmoreItemView = LoadmoreItemView()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        progressBarHolderLoginCL.visibility = View.VISIBLE

        vm.moviesState.observe(this, startObserver)
        vm.getMovies(page)


        setupRV()
    }

    private fun setupRV(){
        val linearLayout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        moviesFragmentRV.apply {
            layoutManager = linearLayout
            adapter = adapterMovies
        }

        moviesFragmentRV.addOnScrollListener(object : PaginationScrollListener(linearLayout){
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoadMore
            }

            override fun loadMoreItems() {
                isLoadMore = true
                page++

                vm.getMovies(page)
            }
        })

    }

    private val startObserver = Observer<MoviesState>{ movieState ->
        when(movieState){
            is MovieDataLoaded ->{
                progressBarHolderLoginCL.visibility = View.GONE
                if (isLoadMore) {
                    // remove loading indicator
                    adapterMovies.remove(loadmoreItemView)
                    isLoadMore = false
                }
                if (page == 1) {
                    adapterMovies.clear()
                }
                movieState.moviesDomain.map {
                    adapterMovies.add(MoviesItemView(it))
                }
            }
            is ErrorState ->{
                progressBarHolderLoginCL.visibility = View.GONE
            }
            is LoadingState ->{
                if (isLoadMore) {
                    // add loading indicator
                    adapterMovies.add(loadmoreItemView)
                }
            }
            is LastPageState ->{
                if (isLoadMore) {
                    // remove loading indicator
                    adapterMovies.remove(loadmoreItemView)
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
                adapterMovies.clear()
            }
        }
    }
}