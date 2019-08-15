package id.mustofa.app.academy.ui.reader

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import id.mustofa.app.academy.R
import id.mustofa.app.academy.util.Const
import id.mustofa.app.academy.util.DummyData
import id.mustofa.app.academy.util.RecyclerViewItemCountAssertion
import org.junit.Rule
import org.junit.Test

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
class CourseReaderActivityTest {

    private val course = DummyData.generateCourses()[0]

    @Rule
    @JvmField
    val activityRule =
        object : ActivityTestRule<CourseReaderActivity>(CourseReaderActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val context = InstrumentationRegistry.getInstrumentation().targetContext
                val intent = Intent(context, CourseReaderActivity::class.java)
                intent.putExtra(Const.EXTRA_COURSE_READ, course.courseId)
                return intent
            }
        }

    @Test
    fun loadModule() {
        Thread.sleep(2500)
        onView(withId(R.id.rvModuleList)).check(matches(isDisplayed()))
        onView(withId(R.id.rvModuleList)).check(RecyclerViewItemCountAssertion(7))
    }

    @Test
    fun clickModule() {
        Thread.sleep(2500)
        onView(withId(R.id.rvModuleList)).check(matches(isDisplayed()))
        onView(withId(R.id.rvModuleList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.webView)).check(matches(isDisplayed()))
    }
}