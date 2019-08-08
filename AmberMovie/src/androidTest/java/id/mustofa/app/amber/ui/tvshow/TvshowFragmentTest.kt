package id.mustofa.app.amber.ui.tvshow

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
 * Indonesia on 07/08/19
 */
class TvshowFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java)
    private val tvshowFragment = TvshowFragment()

    @Before
    fun setup() {
        activityRule.activity.bindFragment(tvshowFragment, "TV_SHOW_TEST")
    }

    @Test
    fun loadTvshows() {
        onView(withId(R.id.rvTvshowFragment)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvshowFragment)).check(RecyclerViewItemCountAssertion(10))
    }
}