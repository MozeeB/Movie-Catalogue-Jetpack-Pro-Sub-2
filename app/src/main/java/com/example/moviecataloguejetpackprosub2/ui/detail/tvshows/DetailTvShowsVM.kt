package com.example.moviecataloguejetpackprosub2.ui.detail.tvshows

import androidx.lifecycle.MutableLiveData
import com.example.moviecataloguejetpackprosub2.BuildConfig
import com.example.moviecataloguejetpackprosub2.data.database.dao.TvShowDao
import com.example.moviecataloguejetpackprosub2.data.repository.DetailRepository
import com.example.moviecataloguejetpackprosub2.domain.DetailTvShowDomain
import com.example.moviecataloguejetpackprosub2.helper.EspressoIdlingResource
import com.example.moviecataloguejetpackprosub2.helper.MOVIE
import com.example.moviecataloguejetpackprosub2.helper.RxUtils
import com.example.moviecataloguejetpackprosub2.ui.base.BaseViewModel
import java.util.concurrent.Executor

sealed class DetailtvShowsState
data class ErrorState(var msg:String?) : DetailtvShowsState()
data class DetailTvShowDataLoaded(val detailTvShowDomain: DetailTvShowDomain) : DetailtvShowsState()
data class FavTvShowsDataFound(val detailTvShowDomain: List<DetailTvShowDomain>) : DetailtvShowsState()

object FavTvShowSaved : DetailtvShowsState()
object RemoveTvShowFav : DetailtvShowsState()
class DetailTvShowsVM (val repository: DetailRepository, val tvShowDao: TvShowDao, val executor: Executor) : BaseViewModel(){

    val detailtvShowsState = MutableLiveData<DetailtvShowsState>()

    fun saveFavTvShow(tvshow : DetailTvShowDomain){
        executor.execute { tvShowDao.saveFavTvShows(tvshow) }
        detailtvShowsState.value =
            FavTvShowSaved
    }

    fun removeTvShowFav(id: Int){
        executor.execute { tvShowDao.removeTvShows(id) }
        detailtvShowsState.value =
            RemoveTvShowFav
    }

    fun checkFavTvShows(id: Int){
        compositeDisposable.add(
            tvShowDao.getFavTvShowById(id)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()){
                        detailtvShowsState.value =
                            FavTvShowsDataFound(
                                result
                            )
                    }
                }, this::onError)
        )
    }

    fun getDetailTvShows(id:String){
        EspressoIdlingResource.increment()
        compositeDisposable.add(
            repository.getDetailTvShows(id, BuildConfig.API_KEY, MOVIE.LANG)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    detailtvShowsState.value =
                        DetailTvShowDataLoaded(
                            result
                        )
                    if (!EspressoIdlingResource.getEspressoIdlingResource()?.isIdleNow!!) {
                        EspressoIdlingResource.decrement()
                    }
                }, this::onError)
        )
    }
    override fun onError(error: Throwable) {
        detailtvShowsState.value = ErrorState(error.localizedMessage)
    }

}