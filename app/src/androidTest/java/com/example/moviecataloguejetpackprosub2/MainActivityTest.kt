package com.example.moviecataloguejetpackprosub2

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.moviecataloguejetpackprosub2.helper.EspressoIdlingResource
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest{

    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)


    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun toMovieActivityTest() {
        Espresso.onView(withId(R.id.navigation_movies)).check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.navigation_movies)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.moviesFragmentRV)).check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.moviesFragmentRV)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
        Espresso.onView(withId(R.id.moviesFragmentRV)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.dateMovieDetailFragmentTV)).check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.favLoveFragmentDetailIB)).check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.favLoveFragmentDetailIB)).perform(ViewActions.click())
        Espresso.pressBack()


        //TV Shows
        Espresso.onView(withId(R.id.navigation_tvshows)).check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.navigation_tvshows)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.tvshowsFragmentRV)).check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvshowsFragmentRV)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
        Espresso.onView(withId(R.id.tvshowsFragmentRV)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.dateMovieDetailFragmentTV)).check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.favLoveTvShowsFragmentDetailIB)).check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.favLoveTvShowsFragmentDetailIB)).perform(ViewActions.click())
        Espresso.pressBack()

        Espresso.onView(withId(R.id.navigation_favorite)).check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.navigation_favorite)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.favMoviesRV)).check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.favMoviesRV)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        Espresso.onView(withId(R.id.favMoviesRV)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.dateMovieDetailFragmentTV)).check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.favLoveFragmentDetailIB)).check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.favLoveFragmentDetailIB)).perform(ViewActions.click())
        Espresso.pressBack()

        Espresso.onView(allOf(withText("TV SHOWS"), isDescendantOfA(withId(R.id.viewpager_main))))
            .perform(ViewActions.click())
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.favTvshowsRV)).check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.favTvshowsRV)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        Espresso.onView(withId(R.id.favTvshowsRV)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.favLoveTvShowsFragmentDetailIB)).check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.favLoveTvShowsFragmentDetailIB)).perform(ViewActions.click())
        Espresso.pressBack()

    }
}