package com.mclowicz.mcmovie

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mclowicz.mcmovie.features.home.components.HomeScreenComposer.Companion.TITLE_MOST_POPULAR_MOVIES
import com.mclowicz.mcmovie.features.home.components.HomeScreenComposer.Companion.TITLE_MOST_POPULAR_TV_SHOWS
import com.mclowicz.mcmovie.features.home.components.HomeScreenComposer.Companion.TITLE_TOP_RATED_MOVIES
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.allOf
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFeatureTest : BaseUITest() {

    @Test
    fun displayScreenTitle() {
        activityRule.launchActivity(Intent())
        assertDisplayed("mcMovie")
    }

    @Test
    fun displayListOfScreenComponents() {

        assertRecyclerViewItemCount(R.id.homeRecyclerView, 6)

        waitForLoad()

        onView(
            allOf(
                withId(R.id.componentTitle),
                ViewMatchers.isDescendantOfA(nthChildOf(withId(R.id.homeRecyclerView), 1))
            )
        )
            .check(ViewAssertions.matches(ViewMatchers.withText(TITLE_MOST_POPULAR_MOVIES)))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(
            allOf(
                withId(R.id.componentTitle),
                ViewMatchers.isDescendantOfA(nthChildOf(withId(R.id.homeRecyclerView), 2))
            )
        )
            .check(ViewAssertions.matches(ViewMatchers.withText(TITLE_TOP_RATED_MOVIES)))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}