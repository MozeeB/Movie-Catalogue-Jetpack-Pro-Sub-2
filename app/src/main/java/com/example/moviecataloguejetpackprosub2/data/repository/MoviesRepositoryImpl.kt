package com.example.moviecataloguejetpackprosub2.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecataloguejetpackprosub2.data.database.dao.MoviesDao
import com.example.moviecataloguejetpackprosub2.data.mapper.MoviesMapper
import com.example.moviecataloguejetpackprosub2.data.service.GlobalService
import com.example.moviecataloguejetpackprosub2.domain.DetailMovieDomain
import com.example.moviecataloguejetpackprosub2.domain.MoviesDomain
import com.example.moviecataloguejetpackprosub2.helper.APIResponse
import com.example.moviecataloguejetpackprosub2.helper.AppExecutors
import com.example.moviecataloguejetpackprosub2.helper.NetworkBoundResource
import com.example.moviecataloguejetpackprosub2.helper.Resource
import io.reactivex.Single

class MoviesRepositoryImpl(
    private val service: GlobalService,
    private val moviesMapper: MoviesMapper,
    private val moviesDao: MoviesDao,
    val executors: AppExecutors

) : MoviesRepository{
    override fun getMovies(
        apiKey: String,
        language: String,
        shortBy: String,
        page:Int
    ): Single<List<MoviesDomain>> {
        return service.getMovies(apiKey, language, shortBy, page).map {
            moviesMapper.mapToListDomain(it.results)
        }
    }

    fun getMoviesAsPaged(): DataSource.Factory<Int, DetailMovieDomain> {
        return moviesDao.getFavMoviesPagination()
    }

    fun getMoviesAsPage() : LiveData<Resource<PagedList<DetailMovieDomain>>>{
        return object :NetworkBoundResource<PagedList<DetailMovieDomain>, List<DetailMovieDomain>>(executors){
            override fun loadDataFromDB(): LiveData<PagedList<DetailMovieDomain>> {
                return LivePagedListBuilder(
                    getMoviesAsPaged(), 20
                ).build()
            }

            override fun shouldFetch(data: PagedList<DetailMovieDomain>): Boolean? {
                return false
            }

            override fun createCall(): LiveData<APIResponse<List<DetailMovieDomain>>>? {
                return null
            }

            override fun saveCallResult(data: List<DetailMovieDomain>) {

            }
        }.asLiveData()
    }
}