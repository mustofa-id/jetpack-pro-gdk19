package id.mustofa.app.academy.ui.bookmark

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import id.mustofa.app.academy.R
import id.mustofa.app.academy.testing.SingleFragmentActivity
import id.mustofa.app.academy.util.RecyclerViewItemCountAssertion
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author Habib Mustofa
 * Indonesia on 04/08/19
 */
class BookmarkFragmentTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java)
    private val bookmarkFragment = BookmarkFragment()

    @Before
    fun setup() {
        activityRule.activity.setFragment(bookmarkFragment)
    }

    @Test
    fun loadCourses() {
        Thread.sleep(2500)
        onView(withId(R.id.rvBookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.rvBookmark)).check(RecyclerViewItemCountAssertion(5))
    }
}