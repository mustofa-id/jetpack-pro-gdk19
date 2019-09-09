package id.mustofa.app.amber.ui.detail

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import id.mustofa.app.amber.R
import id.mustofa.app.amber.data.FakeAndroidMovieData
import id.mustofa.app.amber.util.*
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author Habib Mustofa
 * Indonesia on 07/08/19
 */
class DetailMovieActivityTest {

    private val movie = FakeAndroidMovieData.getMovie()

    @get:Rule
    val activityRule =
        object : ActivityTestRule<DetailMovieActivity>(DetailMovieActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val context = InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(context, DetailMovieActivity::class.java).apply {
                    putExtra(Const.EXTRA_MOVIE_ID, movie.id)
                    putExtra(Const.EXTRA_MOVIE_TYPE, MediaType.MOVIE)
                }
            }
        }

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun teardown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.textMovieDetailTitle))
            .check(matches(allOf(isDisplayed(), withText(movie.title))))

        onView(withId(R.id.textMovieDetailDate))
            .check(matches(allOf(isDisplayed(), withText(movie.releaseDate))))

        onView(withId(R.id.rateMovieDetailRating))
            .check(matches(isDisplayed()))
            .check(RatingBarValueAssertion(movie.voteAverage / 2))

        onView(withId(R.id.textMovieDetailOverview))
            .check(matches(allOf(isDisplayed(), withText(movie.overview))))

        onView(withId(R.id.cgMovieDetailGenres))
            .check(matches(chipsCount(`is`(movie.genres.size))))
            .check(matches(chipsValue(movie.genres.map { it.name })))
    }

    @Test
    fun loadImages() {
        onView(withId(R.id.imgMovieDetailPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.imgMovieDetailBackdrop)).check(matches(isDisplayed()))
    }

    @Test
    fun toggleFavorite() {
        try {
            onView(withId(R.id.btnFavorite)).perform(click())
            onView(withText(R.string.msg_added_favorite)).check(matches(isDisplayed()))
        } catch (e: NoMatchingViewException) {
            onView(withId(R.id.btnFavorite)).perform(click())
            onView(withText(R.string.msg_removed_favorite)).check(matches(isDisplayed()))
        }
    }
}