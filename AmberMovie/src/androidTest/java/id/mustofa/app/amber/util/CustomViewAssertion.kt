package id.mustofa.app.amber.util

import android.view.View
import android.widget.RatingBar
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertNotNull

/**
 * @author Habib Mustofa
 * Indonesia on 06/08/19
 */
class RecyclerViewItemCountAssertion(private val expectedCount: Int) : ViewAssertion {

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) throw noViewFoundException

        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        assertNotNull(adapter)
        assertThat(adapter?.itemCount, `is`(expectedCount))
    }
}

class RatingBarValueAssertion(private val value: Float) : ViewAssertion {

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) throw noViewFoundException

        val ratingBar = view as RatingBar
        assertNotNull(ratingBar)
        assertThat(ratingBar.rating, `is`(value))
    }
}
