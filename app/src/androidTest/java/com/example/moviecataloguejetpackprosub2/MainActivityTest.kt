package com.example.moviecataloguejetpackprosub2

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.example.moviecataloguejetpackprosub2.helper.EspressoIdlingResource
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
        Espresso.onView(withId(R.id.navigation_movies)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)
        Espresso.onView(withId(R.id.navigation_movies)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.moviesFragmentRV)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)
        Espresso.onView(withId(R.id.moviesFragmentRV)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
        Espresso.onView(withId(R.id.moviesFragmentRV)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.dateMovieDetailFragmentTV)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)
        Espresso.pressBack()

        Thread.sleep(1000)
        Espresso.onView(withId(R.id.navigation_tvshows)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)
        Espresso.onView(withId(R.id.navigation_tvshows)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.tvshowsFragmentRV)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)
        Espresso.onView(withId(R.id.tvshowsFragmentRV)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
        Espresso.onView(withId(R.id.tvshowsFragmentRV)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.dateMovieDetailFragmentTV)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)
        Espresso.pressBack()


    }
}