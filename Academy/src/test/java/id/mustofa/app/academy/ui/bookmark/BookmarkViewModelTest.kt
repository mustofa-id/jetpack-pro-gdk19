package id.mustofa.app.academy.ui.bookmark

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/**
 * @author Habib Mustofa
 * Indonesia on 04/08/19
 */
class BookmarkViewModelTest {

    private lateinit var viewModel: BookmarkViewModel

    @Before
    fun setup() {
        viewModel = BookmarkViewModel()
    }

    @Test
    fun course() {
        val bookmarks = viewModel.bookmarks()
        assertNotNull(bookmarks)
        assertEquals(5, bookmarks.size)
    }
}