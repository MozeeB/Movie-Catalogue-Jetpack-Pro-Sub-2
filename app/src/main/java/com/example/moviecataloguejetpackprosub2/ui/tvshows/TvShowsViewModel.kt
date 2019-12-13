package com.example.moviecataloguejetpackprosub2.ui.tvshows

import androidx.lifecycle.MutableLiveData
import com.example.moviecataloguejetpackprosub2.BuildConfig
import com.example.moviecataloguejetpackprosub2.data.repository.TvShowsRepository
import com.example.moviecataloguejetpackprosub2.domain.TvShowDomain
import com.example.moviecataloguejetpackprosub2.helper.EspressoIdlingResource
import com.example.moviecataloguejetpackprosub2.helper.MOVIE
import com.example.moviecataloguejetpackprosub2.helper.RxUtils
import com.example.moviecataloguejetpackprosub2.ui.base.BaseViewModel

sealed class TvShowsState
data class ErrorState(var msg:String?) : TvShowsState()
data class TvShowDataLoaded(val tvShowDomain: List<TvShowDomain>) : TvShowsState()
class TvShowsViewModel (val repository: TvShowsRepository) : BaseViewModel(){

    val tvShowsState = MutableLiveData<TvShowsState>()

    fun getTvShows(){
        EspressoIdlingResource.increment()
        compositeDisposable.add(
            repository.getTvShows(BuildConfig.API_KEY, MOVIE.LANG, MOVIE.SORT_BY)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()){
                        tvShowsState.value = TvShowDataLoaded(result)
                        if (!EspressoIdlingResource.getEspressoIdlingResource()?.isIdleNow!!) {
                            EspressoIdlingResource.decrement()
                        }
                    }
                }, this::onError)
        )
    }
    override fun onError(error: Throwable) {
        tvShowsState.value = ErrorState(error.localizedMessage)
    }

}
