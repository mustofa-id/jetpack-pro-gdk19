package id.mustofa.app.academy.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import id.mustofa.app.academy.data.Course
import id.mustofa.app.academy.data.Module
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
class DetailCourseViewModelTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailCourseViewModel
    private var academyRepository = mock(AcademyRepository::class.java)
    private val course = FakeData.generateCourses()[0]
    private val courseId = course.courseId
    private val modules = FakeData.generateModules(courseId)

    @Before
    fun setup() {
        viewModel = DetailCourseViewModel(academyRepository)
        viewModel.courseId = course.courseId
    }

    @Test
    fun course() {
        val liveCourse = MutableLiveData<Course>()
        liveCourse.value = course

        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(liveCourse)

        @Suppress("UNCHECKED_CAST")
        val observer = mock(Observer::class.java) as Observer<Course?>

        viewModel.course().observeForever(observer)

        verify(observer).onChanged(course)
    }

    @Test
    fun modules() {
        val liveModule = MutableLiveData<List<Module>>()
        liveModule.value = modules

        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(liveModule)

        @Suppress("UNCHECKED_CAST")
        val observer = mock(Observer::class.java) as Observer<List<Module>>

        viewModel.modules().observeForever(observer)

        verify(observer).onChanged(modules)
    }
}