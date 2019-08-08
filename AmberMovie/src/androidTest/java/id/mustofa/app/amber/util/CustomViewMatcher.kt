package id.mustofa.app.amber.util

import android.view.View
import androidx.core.view.children
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.tabs.TabLayout
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

/**
 * @author Habib Mustofa
 * Indonesia on 08/08/19
 */
fun chipsCount(countMatcher: Matcher<Int>): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description?) {
            description?.appendText("ChipGroup with chips count ")
            countMatcher.describeTo(description)
        }

        override fun matchesSafely(item: View?): Boolean {
            return item is ChipGroup && countMatcher.matches(item.childCount)
        }
    }
}

fun chipsValue(expectedValues: List<String>): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description?) {
            description?.appendText("Matching Chips text with expectedValues=$expectedValues")
        }

        override fun matchesSafely(item: View?): Boolean {
            return item is ChipGroup && item.children
                .map { (it as Chip).text }
                .zip(expectedValues.asSequence())
                .all { (text, value) -> text == value }
        }
    }
}

fun tabsCount(expectedCount: Int): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description?) {
            description?.appendText("TabLayout with tabs count $expectedCount")
        }

        override fun matchesSafely(item: View?): Boolean {
            return item is TabLayout && `is`(expectedCount).matches(item.tabCount)
        }
    }
}

fun tabTitle(position: Int, expectedTitle: String): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description?) {
            description?.appendText("Tab with title $expectedTitle")
        }

        override fun matchesSafely(item: View?): Boolean {
            return item is TabLayout && `is`(expectedTitle).matches(item.getTabAt(position)?.text)
        }
    }
}