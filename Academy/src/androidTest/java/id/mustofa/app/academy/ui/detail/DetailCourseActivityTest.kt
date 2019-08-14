package id.mustofa.app.academy.ui.detail

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
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
 * Indonesia on 04/08/19
 */
class DetailCourseActivityTest {

    private val dummyCourse = DummyData.generateCourses()[0]

    @Rule
    @JvmField
    val activityRule =
        object : ActivityTestRule<DetailCourseActivity>(DetailCourseActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val context = InstrumentationRegistry.getInstrumentation().targetContext
                val intent = Intent(context, DetailCourseActivity::class.java)
                intent.putExtra(Const.EXTRA_COURSE_DETAIL, dummyCourse.courseId)
                return intent
            }
        }

    @Test
    fun loadCourse() {
        onView(withId(R.id.textDetailTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.textDetailTitle)).check(matches(withText(dummyCourse.title)))
        onView(withId(R.id.textDetailDate)).check(matches(isDisplayed()))
        onView(withId(R.id.textDetailDate))
            .check(matches(withText(String.format("Deadline %s", dummyCourse.deadline))))
    }

    @Test
    fun loadModule() {
        onView(withId(R.id.rvModule)).check(matches(isDisplayed()))
        onView(withId(R.id.rvModule)).check(RecyclerViewItemCountAssertion(7))
    }
}