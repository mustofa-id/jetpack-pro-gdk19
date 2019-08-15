package id.mustofa.app.academy.ui.bookmark

import id.mustofa.app.academy.data.source.AcademyRepository
import id.mustofa.app.academy.util.FakeData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

/**
 * @author Habib Mustofa
 * Indonesia on 04/08/19
 */
class BookmarkViewModelTest {

    private lateinit var viewModel: BookmarkViewModel
    private var academyRepository = Mockito.mock(AcademyRepository::class.java)

    @Before
    fun setup() {
        viewModel = BookmarkViewModel(academyRepository)
    }

    @Test
    fun course() {
        `when`(academyRepository.getBookmarkedCourses()).thenReturn(FakeData.generateCourses())
        val bookmarks = viewModel.bookmarks()
        verify(academyRepository).getBookmarkedCourses()
        assertNotNull(bookmarks)
        assertEquals(5, bookmarks.size)
    }
}