package com.example.moviecataloguejetpackprosub2.ui.favorite.tvshows

import androidx.lifecycle.MutableLiveData
import com.example.moviecataloguejetpackprosub2.data.database.dao.TvShowDao
import com.example.moviecataloguejetpackprosub2.domain.DetailTvShowDomain
import com.example.moviecataloguejetpackprosub2.helper.RxUtils
import com.example.moviecataloguejetpackprosub2.ui.base.BaseViewModel
import java.util.concurrent.Executor

sealed class FavTvShowsState
data class FavMoviesDataLoaded(val movieDomain: List<DetailTvShowDomain>) : FavTvShowsState()
data class ErrorState(var msg:String?) : FavTvShowsState()
object NoDataFound : FavTvShowsState()
class FavTvShowsVM (val tvShowDao: TvShowDao, val executor: Executor) : BaseViewModel(){

    val favMoviesState = MutableLiveData<FavTvShowsState>()

    fun getFavTvShows(){
        compositeDisposable.add(
            tvShowDao.getFavTvShow()
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()){
                        favMoviesState.value = FavMoviesDataLoaded(result)
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