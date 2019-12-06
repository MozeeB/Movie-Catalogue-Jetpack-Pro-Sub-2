package com.example.moviecataloguejetpackprosub2.ui.movies

import androidx.lifecycle.MutableLiveData
import com.example.moviecataloguejetpackprosub2.BuildConfig
import com.example.moviecataloguejetpackprosub2.data.repository.MoviesRepository
import com.example.moviecataloguejetpackprosub2.domain.MoviesDomain
import com.example.moviecataloguejetpackprosub2.helper.EspressoIdlingResource
import com.example.moviecataloguejetpackprosub2.helper.MOVIE
import com.example.moviecataloguejetpackprosub2.helper.RxUtils
import com.example.moviecataloguejetpackprosub2.ui.base.BaseViewModel

sealed class MoviesState
data class ErrorState(var msg:String?) : MoviesState()
data class MovieDataLoaded(val moviesDomain: List<MoviesDomain>) : MoviesState()
class MoviesViewModel (val repository: MoviesRepository) : BaseViewModel(){

    val moviesState = MutableLiveData<MoviesState>()

    fun getMovies(){
        EspressoIdlingResource.increment()
        compositeDisposable.add(
            repository.getMovies(BuildConfig.API_KEY, MOVIE.LANG, MOVIE.SORT_BY)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()){
                        moviesState.value = MovieDataLoaded(result)
                        EspressoIdlingResource.decrement()

                    }
                }, this::onError)
        )
    }
    override fun onError(error: Throwable) {
        moviesState.value = ErrorState(error.localizedMessage)
    }

}
