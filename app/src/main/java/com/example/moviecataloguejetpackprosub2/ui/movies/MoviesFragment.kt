package com.example.moviecataloguejetpackprosub2.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecataloguejetpackprosub2.BuildConfig
import com.example.moviecataloguejetpackprosub2.R
import com.example.moviecataloguejetpackprosub2.helper.MOVIE
import com.example.moviecataloguejetpackprosub2.helper.view.MoviesItemView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_movies.*
import org.koin.android.ext.android.inject

class MoviesFragment : Fragment() {

    private val vm:MoviesViewModel by inject()

    private val adapterMovies = GroupAdapter<ViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vm.moviesState.observe(this, startObserver)
        vm.getMovies(BuildConfig.API_KEY, MOVIE.LANG, MOVIE.SORT_BY)

        moviesFragmentRV.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterMovies
        }

    }

    private val startObserver = Observer<MoviesState>{ movieState ->
        when(movieState){
            is MovieDataLoaded ->{
                adapterMovies.clear()
                movieState.moviesDomain.map {
                    adapterMovies.add(MoviesItemView(it))
                }
            }
            is ErrorState ->{

            }
        }
    }
}