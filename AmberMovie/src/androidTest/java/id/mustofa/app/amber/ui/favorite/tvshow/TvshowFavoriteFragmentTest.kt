package id.mustofa.app.amber.ui.favorite.tvshow

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import id.mustofa.app.amber.R
import id.mustofa.app.amber.SingleFragmentActivity
import id.mustofa.app.amber.data.source.local.AppDatabase
import id.mustofa.app.amber.data.source.local.MovieLocalDataSource
import id.mustofa.app.amber.util.EspressoIdlingResource
import id.mustofa.app.amber.util.MediaType
import id.mustofa.app.amber.util.RecyclerViewItemCountAssertion
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author Habib Mustofa
 * Indonesia on 06/09/19
 */
class TvshowFavoriteFragmentTest {

    private lateinit var db: AppDatabase
    private lateinit var dao: MovieLocalDataSource

    @get:Rule
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java)
    private val tvshowFavoriteFragment = TvshowFavoriteFragment()

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
        activityRule.activity.bindFragment(tvshowFavoriteFragment, "TVSHOW_FAVORITE_TEST")
        db = AppDatabase(activityRule.activity.applicationContext)
        dao = db.movieLocalDataSource()
    }

    @After
    fun teardown() {
        if (db.isOpen) db.close()
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadFavoriteTvshows() {
        val movieCount = dao.countFavoriteByType(MediaType.TV)
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).check(RecyclerViewItemCountAssertion(movieCount))
    }
}