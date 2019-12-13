package com.example.moviecataloguejetpackprosub2.ui.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.moviecataloguejetpackprosub2.ui.favorite.movies.FavMoviesFragment
import com.example.moviecataloguejetpackprosub2.ui.favorite.tvshows.FavTvShowsFragment

class MyPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){

    private val pages = listOf(
        FavMoviesFragment(),
        FavTvShowsFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "MOVIES"
            else -> "TV SHOWS"
        }
    }
}