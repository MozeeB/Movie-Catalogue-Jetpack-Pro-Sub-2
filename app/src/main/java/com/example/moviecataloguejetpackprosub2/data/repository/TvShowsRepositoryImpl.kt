package com.example.moviecataloguejetpackprosub2.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecataloguejetpackprosub2.data.database.dao.TvShowDao
import com.example.moviecataloguejetpackprosub2.data.mapper.TvShowsMapper
import com.example.moviecataloguejetpackprosub2.data.service.GlobalService
import com.example.moviecataloguejetpackprosub2.domain.DetailTvShowDomain
import com.example.moviecataloguejetpackprosub2.domain.TvShowDomain
import com.example.moviecataloguejetpackprosub2.helper.APIResponse
import com.example.moviecataloguejetpackprosub2.helper.AppExecutors
import com.example.moviecataloguejetpackprosub2.helper.NetworkBoundResource
import com.example.moviecataloguejetpackprosub2.helper.Resource
import io.reactivex.Single

class TvShowsRepositoryImpl(
    private val service: GlobalService,
    private val tvShowsMapper: TvShowsMapper,
    private val executors: AppExecutors,
    private val tvshowsDao : TvShowDao

) : TvShowsRepository{
    override fun getTvShows(
        apiKey: String,
        language: String,
        shortBy: String,
        page:Int
    ): Single<List<TvShowDomain>> {
        return service.getTvShow(apiKey, language, shortBy, page).map {
            tvShowsMapper.mapToListDomain(it.results)
        }
    }

    fun getTvShowsAsPage(): DataSource.Factory<Int, DetailTvShowDomain> {
        return tvshowsDao.getFavMoviesPagination()
    }


    fun getMoviesAsPage() : LiveData<Resource<PagedList<DetailTvShowDomain>>> {
        return object : NetworkBoundResource<PagedList<DetailTvShowDomain>, List<DetailTvShowDomain>>(executors){
            override fun loadDataFromDB(): LiveData<PagedList<DetailTvShowDomain>> {
                return LivePagedListBuilder(
                    getTvShowsAsPage(), 20
                ).build()
            }

            override fun shouldFetch(data: PagedList<DetailTvShowDomain>): Boolean? {
                return false
            }

            override fun createCall(): LiveData<APIResponse<List<DetailTvShowDomain>>>? {
                return null
            }

            override fun saveCallResult(data: List<DetailTvShowDomain>) {

            }
        }.asLiveData()
    }
}