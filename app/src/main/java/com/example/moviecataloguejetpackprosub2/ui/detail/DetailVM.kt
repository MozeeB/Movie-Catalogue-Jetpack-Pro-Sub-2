package com.example.moviecataloguejetpackprosub2.ui.detail

import androidx.lifecycle.MutableLiveData
import com.example.moviecataloguejetpackprosub2.BuildConfig
import com.example.moviecataloguejetpackprosub2.data.repository.DetailRepository
import com.example.moviecataloguejetpackprosub2.domain.DetailMovieDomain
import com.example.moviecataloguejetpackprosub2.domain.DetailTvShowDomain
import com.example.moviecataloguejetpackprosub2.helper.EspressoIdlingResource
import com.example.moviecataloguejetpackprosub2.helper.MOVIE
import com.example.moviecataloguejetpackprosub2.helper.RxUtils
import com.example.moviecataloguejetpackprosub2.ui.base.BaseViewModel

sealed class DetailState
data class ErrorState(var msg:String?) : DetailState()
data class DetailMoviesDataLoaded(val detailMovieDomain: DetailMovieDomain) : DetailState()
data class DetailTvShowDataLoaded(val detailTvShowDomain: DetailTvShowDomain) : DetailState()
class DetailVM (val repository: DetailRepository) : BaseViewModel(){

    val detailState = MutableLiveData<DetailState>()

    fun getDetailMovies(id:String){
        EspressoIdlingResource.increment()
        compositeDisposable.add(
            repository.getDetailMovies(id, BuildConfig.API_KEY, MOVIE.LANG)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    detailState.value = DetailMoviesDataLoaded(result)
                    EspressoIdlingResource.decrement()

                }, this::onError)
        )
    }
    fun getDetailTvShows(id:String){
        EspressoIdlingResource.increment()
        compositeDisposable.add(
            repository.getDetailTvShows(id, BuildConfig.API_KEY, MOVIE.LANG)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    detailState.value = DetailTvShowDataLoaded(result)
                    EspressoIdlingResource.decrement()

                }, this::onError)
        )
    }
    override fun onError(error: Throwable) {
        detailState.value = ErrorState(error.localizedMessage)
    }

}
