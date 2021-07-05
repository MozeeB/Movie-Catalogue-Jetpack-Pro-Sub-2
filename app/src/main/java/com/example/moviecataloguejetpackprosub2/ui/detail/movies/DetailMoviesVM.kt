package com.example.moviecataloguejetpackprosub2.ui.detail.movies

import androidx.lifecycle.MutableLiveData
import com.example.moviecataloguejetpackprosub2.BuildConfig
import com.example.moviecataloguejetpackprosub2.data.database.dao.MoviesDao
import com.example.moviecataloguejetpackprosub2.data.database.dao.TvShowDao
import com.example.moviecataloguejetpackprosub2.data.repository.DetailRepository
import com.example.moviecataloguejetpackprosub2.domain.DetailMovieDomain
import com.example.moviecataloguejetpackprosub2.domain.DetailTvShowDomain
import com.example.moviecataloguejetpackprosub2.helper.EspressoIdlingResource
import com.example.moviecataloguejetpackprosub2.helper.MOVIE
import com.example.moviecataloguejetpackprosub2.helper.RxUtils
import com.example.moviecataloguejetpackprosub2.ui.base.BaseViewModel
import java.util.concurrent.Executor

sealed class DetailState
data class ErrorState(var msg:String?) : DetailState()
data class DetailMoviesDataLoaded(val detailMovieDomain: DetailMovieDomain) : DetailState()
data class FavMovieDataFound(val detailMovieDomain: List<DetailMovieDomain>) : DetailState()

object FavMoviesSaved : DetailState()
object RemoveMovieFav : DetailState()

class DetailMoviesVM (val repository: DetailRepository, val moviesDao: MoviesDao, val executor: Executor) : BaseViewModel(){

    val detailState = MutableLiveData<DetailState>()

    fun saveFavMovieSave(movies: DetailMovieDomain){
        executor.execute { moviesDao.saveFavMovies(movies) }
        detailState.value =
            FavMoviesSaved
    }

    fun removeMovieFav(idMovie:Int){
        executor.execute { moviesDao.removeMovie(idMovie) }
        detailState.value =
            RemoveMovieFav
    }

    fun checkFavMovie(id: Int){
        compositeDisposable.add(
            moviesDao.getFavMoviesById(id)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()){
                        detailState.value =
                            FavMovieDataFound(
                                result
                            )
                    }
                }, this::onError)
        )
    }



    fun getDetailMovies(id:String){
        EspressoIdlingResource.increment()
        compositeDisposable.add(
            repository.getDetailMovies(id, BuildConfig.API_KEY, MOVIE.LANG)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    detailState.value =
                        DetailMoviesDataLoaded(
                            result
                        )
                    if (!EspressoIdlingResource.getEspressoIdlingResource()?.isIdleNow!!) {
                        EspressoIdlingResource.decrement()
                    }
                }, this::onError)
        )
    }

    override fun onError(error: Throwable) {
        detailState.value =
            ErrorState(
                error.localizedMessage
            )
    }

}
