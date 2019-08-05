package id.mustofa.app.academy.ui.academy

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
class AcademyFragmentTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java)
    private val academyFragment = AcademyFragment()

    @Before
    fun setup() {
        activityRule.activity.setFragment(academyFragment)
    }

    @Test
    fun loadCourses() {
        onView(withId(R.id.rvAcademy)).check(matches(isDisplayed()))
        onView(withId(R.id.rvAcademy)).check(RecyclerViewItemCountAssertion(5))
    }
}