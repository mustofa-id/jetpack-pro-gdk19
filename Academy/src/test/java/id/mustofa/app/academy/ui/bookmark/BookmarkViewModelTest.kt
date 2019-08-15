package id.mustofa.app.academy.ui.bookmark

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import id.mustofa.app.academy.data.Course
import id.mustofa.app.academy.data.source.AcademyRepository
import id.mustofa.app.academy.util.FakeData
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

/**
 * @author Habib Mustofa
 * Indonesia on 04/08/19
 */
class BookmarkViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: BookmarkViewModel
    private var academyRepository = mock(AcademyRepository::class.java)

    @Before
    fun setup() {
        viewModel = BookmarkViewModel(academyRepository)
    }

    @Test
    fun course() {
        val fakeCourses = FakeData.generateCourses()
        val courses = MutableLiveData<List<Course>>()
        courses.value = fakeCourses
        `when`(academyRepository.getBookmarkedCourses()).thenReturn(courses)

        @Suppress("UNCHECKED_CAST")
        val observer = mock(Observer::class.java) as Observer<List<Course>>
        viewModel.bookmarks().observeForever(observer)

        verify(observer).onChanged(fakeCourses)
    }
}