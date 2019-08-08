package id.mustofa.app.amber.ui.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import id.mustofa.app.amber.R
import id.mustofa.app.amber.SingleFragmentActivity
import id.mustofa.app.amber.util.RecyclerViewItemCountAssertion
import id.mustofa.app.amber.util.bindFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author Habib Mustofa
 * Indonesia on 06/08/19
 */
class MovieFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java)
    private val movieFragment = MovieFragment()

    @Before
    fun setup() {
        activityRule.activity.bindFragment(movieFragment, "MOVIE_TEST")
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rvMovieFragment)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovieFragment)).check(RecyclerViewItemCountAssertion(10))
    }
}