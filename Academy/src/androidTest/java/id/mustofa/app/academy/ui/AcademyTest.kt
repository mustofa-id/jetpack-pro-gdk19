package id.mustofa.app.academy.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import id.mustofa.app.academy.R
import id.mustofa.app.academy.ui.home.HomeActivity
import org.junit.Rule
import org.junit.Test

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
class AcademyTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun toDetailActivity() {
        onView(withId(R.id.rvAcademy)).check(matches(isDisplayed()))
        onView(withId(R.id.rvAcademy)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.textDetailTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.textDetailTitle)).check(matches(withText("Menjadi Android Developer Expert")))
    }

    @Test
    fun toReaderActivity() {
        onView(withId(R.id.rvAcademy)).check(matches(isDisplayed()))
        onView(withId(R.id.rvAcademy)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.btnDetailStart)).check(matches(isDisplayed()))
        onView(withId(R.id.btnDetailStart)).perform(click())

        onView(withId(R.id.frame_container)).check(matches(isDisplayed()))
        onView(withId(R.id.rvModuleList)).check(matches(isDisplayed()))
        onView(withId(R.id.rvModuleList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

        onView(withId(R.id.webView)).check(matches(isDisplayed()))
    }
}