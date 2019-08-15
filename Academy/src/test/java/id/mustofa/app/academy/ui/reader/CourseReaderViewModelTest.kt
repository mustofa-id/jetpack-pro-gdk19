package id.mustofa.app.academy.ui.reader

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import id.mustofa.app.academy.data.Content
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
@Suppress("UNCHECKED_CAST")
class CourseReaderViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CourseReaderViewModel

    private val academyRepository = mock(AcademyRepository::class.java)
    private val course = FakeData.generateCourses()[0]
    private val courseId = course.courseId
    private val modules = FakeData.generateModules(courseId)
    private val moduleId = modules[0].moduleId

    @Before
    fun setup() {
        viewModel = CourseReaderViewModel(academyRepository)
        viewModel.courseId = courseId
    }

    @Test
    fun modules() {
        val liveModules = MutableLiveData<List<Module>>()
        liveModules.value = modules

        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(liveModules)

        val observer = mock(Observer::class.java) as Observer<List<Module>>

        viewModel.modules().observeForever(observer)
        verify(observer).onChanged(modules)
    }

    @Test
    fun module() {
        val liveModule = MutableLiveData<Module>()

        val module = modules[0]
        val contentString =
            "<h3 class=\\\"fr-text-bordered\\\">Modul 0 : Introduction</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>"
        module.content = Content(contentString)

        liveModule.value = module

        `when`(academyRepository.getContent(courseId, moduleId)).thenReturn(liveModule)

        viewModel.moduleId = moduleId

        val observer = mock(Observer::class.java) as Observer<Module?>

        viewModel.module().observeForever(observer)

        verify(observer).onChanged(module)
    }
}