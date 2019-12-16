package com.example.moviecataloguejetpackprosub2.ui.favorite.tvshows

import androidx.lifecycle.MutableLiveData
import com.example.moviecataloguejetpackprosub2.data.database.dao.TvShowDao
import com.example.moviecataloguejetpackprosub2.domain.DetailTvShowDomain
import com.example.moviecataloguejetpackprosub2.helper.EspressoIdlingResource
import com.example.moviecataloguejetpackprosub2.helper.RxUtils
import com.example.moviecataloguejetpackprosub2.ui.base.BaseViewModel

sealed class FavTvShowsState
data class FavMoviesDataLoaded(val movieDomain: List<DetailTvShowDomain>) : FavTvShowsState()
data class ErrorState(var msg:String?) : FavTvShowsState()
object NoDataFound : FavTvShowsState()
class FavTvShowsVM (private val tvShowDao: TvShowDao) : BaseViewModel(){

    val favMoviesState = MutableLiveData<FavTvShowsState>()

    fun getFavTvShows(){
        EspressoIdlingResource.increment()
        compositeDisposable.add(
            tvShowDao.getFavTvShow()
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()){
                        favMoviesState.value = FavMoviesDataLoaded(result)
                        if (!EspressoIdlingResource.getEspressoIdlingResource()?.isIdleNow!!) {
                            EspressoIdlingResource.decrement()
                        }
                    }else{
                        favMoviesState.value = NoDataFound
                    }

                }, this::onError)
        )
    }

    override fun onError(error: Throwable) {
        favMoviesState.value = ErrorState(error.localizedMessage)
    }

}