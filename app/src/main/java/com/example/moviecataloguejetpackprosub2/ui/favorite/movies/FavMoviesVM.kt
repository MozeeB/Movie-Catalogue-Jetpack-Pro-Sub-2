package com.example.moviecataloguejetpackprosub2.ui.favorite.movies

import androidx.lifecycle.MutableLiveData
import com.example.moviecataloguejetpackprosub2.data.database.dao.MoviesDao
import com.example.moviecataloguejetpackprosub2.domain.DetailMovieDomain
import com.example.moviecataloguejetpackprosub2.helper.EspressoIdlingResource
import com.example.moviecataloguejetpackprosub2.helper.RxUtils
import com.example.moviecataloguejetpackprosub2.ui.base.BaseViewModel

sealed class FavMoviesState
data class FavMoviesDataLoaded(val movieDomain: List<DetailMovieDomain>) : FavMoviesState()
data class ErrorState(var msg:String?) : FavMoviesState()
object NoDataFound : FavMoviesState()
class FavMoviesVM (private val moviesDao: MoviesDao) : BaseViewModel(){

    val favMoviesState = MutableLiveData<FavMoviesState>()

    fun getFavMovies(){
        EspressoIdlingResource.increment()
        compositeDisposable.add(
            moviesDao.getFavMovies()
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