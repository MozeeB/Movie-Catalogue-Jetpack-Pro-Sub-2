package com.example.moviecataloguejetpackprosub2.di

import androidx.room.Room
import com.example.moviecataloguejetpackprosub2.data.database.AppDatabase
import com.example.moviecataloguejetpackprosub2.data.mapper.DetailMovieMapper
import com.example.moviecataloguejetpackprosub2.data.mapper.DetailTvShowMapper
import com.example.moviecataloguejetpackprosub2.data.mapper.MoviesMapper
import com.example.moviecataloguejetpackprosub2.data.mapper.TvShowsMapper
import com.example.moviecataloguejetpackprosub2.data.repository.*
import com.example.moviecataloguejetpackprosub2.data.service.GlobalInterceptor
import com.example.moviecataloguejetpackprosub2.data.service.GlobalService
import com.example.moviecataloguejetpackprosub2.helper.LOCAL
import com.example.moviecataloguejetpackprosub2.helper.MOVIE
import com.example.moviecataloguejetpackprosub2.ui.detail.movies.DetailMoviesVM
import com.example.moviecataloguejetpackprosub2.ui.detail.tvshows.DetailTvShowsVM
import com.example.moviecataloguejetpackprosub2.ui.favorite.movies.FavMoviesVM
import com.example.moviecataloguejetpackprosub2.ui.favorite.tvshows.FavTvShowsVM
import com.example.moviecataloguejetpackprosub2.ui.movies.MoviesViewModel
import com.example.moviecataloguejetpackprosub2.ui.tvshows.TvShowsViewModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

val appModule = module {

    single { GlobalInterceptor() }
    single { createOkHttpClient(get()) }
    single { createWebService<GlobalService>(get(), MOVIE.BASE_URL) }

    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            LOCAL.NAME_DATABASE
        ).build()
    }

}

val dataModule = module {


    single { getExecutor() }

    //db dao
    single { get<AppDatabase>().moviesDao() }
    single { get<AppDatabase>().tvshowDao() }

    //repository
    single { MoviesRepositoryImpl(get(), get()) as MoviesRepository }
    single { TvShowsRepositoryImpl(get(), get()) as TvShowsRepository }
    single { DetailRepositoryImpl(get(), get(), get()) as DetailRepository }

    //mapper
    single { MoviesMapper() }
    single { TvShowsMapper() }
    single { DetailMovieMapper() }
    single { DetailTvShowMapper() }

    //viewmodel
    viewModel { MoviesViewModel(get()) }
    viewModel { TvShowsViewModel(get()) }
    viewModel {
        DetailMoviesVM(
            get(),
            get(),
            get()
        )
    }
    viewModel { FavMoviesVM(get()) }
    viewModel { FavTvShowsVM(get()) }
    viewModel { DetailTvShowsVM(get(), get(), get()) }

}
fun createOkHttpClient(interceptor: GlobalInterceptor): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val timeout = 60L
    return OkHttpClient.Builder()
        .connectTimeout(timeout, TimeUnit.SECONDS)
        .readTimeout(timeout, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(interceptor)
        .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .create()
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}

fun getExecutor(): Executor {
    return Executors.newFixedThreadPool(2)
}
val myAppModule = listOf(appModule, dataModule)