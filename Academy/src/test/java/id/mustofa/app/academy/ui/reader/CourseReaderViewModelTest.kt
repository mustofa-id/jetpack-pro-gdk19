package id.mustofa.app.academy.ui.reader

import id.mustofa.app.academy.data.Content
import id.mustofa.app.academy.data.source.AcademyRepository
import id.mustofa.app.academy.util.FakeData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

/**
 * @author Habib Mustofa
 * Indonesia on 04/08/19
 */
class CourseReaderViewModelTest {

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
        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(modules)
        val modules = viewModel.modules()
        verify(academyRepository).getAllModulesByCourse(courseId)
        assertNotNull(modules)
        assertEquals(7, modules.size)
    }

    @Test
    fun module() {
        val module = modules[0]
        val contentString =
            "<h3 class=\\\"fr-text-bordered\\\">Modul 0 : Introduction</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>"
        module.content = Content(contentString)
        viewModel.moduleId = moduleId

        `when`(academyRepository.getContent(courseId, moduleId)).thenReturn(module)
        val selectedModule = viewModel.module()
        verify(academyRepository).getContent(courseId, moduleId)
        assertNotNull(selectedModule)

        val content = selectedModule.content
        assertNotNull(content)

        val contentValue = content?.content
        assertNotNull(contentValue)

        assertEquals(contentString, contentValue)
    }
}