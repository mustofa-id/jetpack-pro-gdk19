package id.mustofa.app.amber.ui

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import id.mustofa.app.amber.R
import id.mustofa.app.amber.ui.home.HomeActivity
import id.mustofa.app.amber.util.EspressoIdlingResource
import id.mustofa.app.amber.util.tabTitle
import id.mustofa.app.amber.util.tabsCount
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author Habib Mustofa
 * Indonesia on 08/08/19
 */
class AmberMovieTest {

    @get:Rule
    val activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun teardown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun tabsAndPages() {
        onView(withId(R.id.homeTab))
            .check(matches(allOf(tabsCount(2), tabTitle(0, "Movie"), tabTitle(1, "Tv Show"))))
    }

    @Test
    fun movieToDetail() {
        // navigate to discover
        onView(withId(R.id.nav_discover)).perform(click())
        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.homeToolbar))))
            .check(matches(withText(R.string.title_nav_discover)))

        // discover movie
        onView(allOf(withId(R.id.rvMovie), isDisplayed()))
            .check(matches(isDisplayed()))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )
        onView(withId(R.id.textMovieDetailTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.btnFavorite)).perform(click())
        try {
            onView(withText(R.string.msg_added_favorite)).check(matches(isDisplayed()))
        } catch (e: NoMatchingViewException) {
            onView(withText(R.string.msg_removed_favorite)).check(matches(isDisplayed()))
        }

        pressBack()

        // navigate to favorite
        onView(withId(R.id.nav_favorite)).perform(click())
        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.homeToolbar))))
            .check(matches(withText(R.string.title_nav_favorite)))

        try {
            // favorite movie
            onView(allOf(withId(R.id.rvMovie), isDisplayed()))
                .check(matches(isDisplayed()))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
                )
            onView(withId(R.id.textMovieDetailTitle)).check(matches(isDisplayed()))
        } catch (e: PerformException) {
            // empty favorite movie
            onView(allOf(withId(R.id.emptyMovie), isDisplayed())).check(matches(isDisplayed()))
        }
    }

    @Test
    fun tvshowToDetail() {
        onView(withId(R.id.homeViewPager)).perform(swipeLeft())

        // navigate to discover
        onView(withId(R.id.nav_discover)).perform(click())
        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.homeToolbar))))
            .check(matches(withText(R.string.title_nav_discover)))

        // discover tvshow
        onView(allOf(withId(R.id.rvMovie), isDisplayed()))
            .check(matches(isDisplayed()))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )
        onView(withId(R.id.textMovieDetailTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.btnFavorite)).perform(click())
        try {
            onView(withText(R.string.msg_added_favorite)).check(matches(isDisplayed()))
        } catch (e: NoMatchingViewException) {
            onView(withText(R.string.msg_removed_favorite)).check(matches(isDisplayed()))
        }

        pressBack()

        // navigate to favorite
        onView(withId(R.id.nav_favorite)).perform(click())
        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.homeToolbar))))
            .check(matches(withText(R.string.title_nav_favorite)))

        try {
            // favorite tvshow
            onView(allOf(withId(R.id.rvMovie), isDisplayed()))
                .check(matches(isDisplayed()))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
                )
            onView(withId(R.id.textMovieDetailTitle)).check(matches(isDisplayed()))
        } catch (e: PerformException) {
            // empty favorite tvshow
            onView(allOf(withId(R.id.emptyMovie), isDisplayed())).check(matches(isDisplayed()))
        }
    }
}