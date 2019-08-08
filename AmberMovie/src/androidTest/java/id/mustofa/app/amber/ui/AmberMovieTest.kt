package id.mustofa.app.amber.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import id.mustofa.app.amber.R
import id.mustofa.app.amber.ui.home.HomeActivity
import id.mustofa.app.amber.util.tabTitle
import id.mustofa.app.amber.util.tabsCount
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test

/**
 * @author Habib Mustofa
 * Indonesia on 08/08/19
 */
class AmberMovieTest {

    @get:Rule
    val activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun tabsAndPages() {
        onView(withId(R.id.homeTab))
            .check(matches(tabsCount(2)))
            .check(
                matches(
                    allOf(
                        tabTitle(0, "Movie"),
                        tabTitle(1, "Tv Show")
                    )
                )
            )
    }

    @Test
    fun toDetailMovieActivity() {
        onView(withId(R.id.rvMovieFragment)).check(matches(isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.textMovieDetailTitle)).check(
            matches(allOf(isDisplayed(), withText("Avenger Infinity War")))
        )
        pressBack()
        onView(withId(R.id.rvMovieFragment)).perform(swipeLeft())
        onView(withId(R.id.rvTvshowFragment)).check(matches(isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.textMovieDetailTitle)).check(
            matches(allOf(isDisplayed(), withText("Arrow")))
        )
    }
}