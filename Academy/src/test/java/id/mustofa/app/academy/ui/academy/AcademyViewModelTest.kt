package id.mustofa.app.academy.ui.academy

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
class AcademyViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AcademyViewModel
    private var academyRepository = mock(AcademyRepository::class.java)

    @Before
    fun setup() {
        viewModel = AcademyViewModel(academyRepository)
    }

    @Test
    fun courses() {
        val fakeCourses = FakeData.generateCourses()
        val liveCourses = MutableLiveData<List<Course>>()
        liveCourses.value = fakeCourses
        `when`(academyRepository.getAllCourses()).thenReturn(liveCourses)

        @Suppress("UNCHECKED_CAST")
        val observer = mock(Observer::class.java) as Observer<List<Course>>

        viewModel.courses().observeForever(observer)

        verify(observer).onChanged(fakeCourses)
    }
}