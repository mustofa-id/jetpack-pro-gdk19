package id.mustofa.app.academy.ui.detail

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
class DetailCourseViewModelTest {

    private lateinit var viewModel: DetailCourseViewModel
    private var academyRepository = Mockito.mock(AcademyRepository::class.java)
    private val course = FakeData.generateCourses()[0]
    private val courseId = course.courseId

    @Before
    fun setup() {
        viewModel = DetailCourseViewModel(academyRepository)
        viewModel.courseId = course.courseId
    }

    @Test
    fun course() {
        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(course)
        val vmCourse = viewModel.course()
        verify(academyRepository).getCourseWithModules(courseId)
        assertNotNull(vmCourse)
        assertNotNull(vmCourse.courseId)
        assertEquals(course, vmCourse)
    }

    @Test
    fun modules() {
        `when`(academyRepository.getAllModulesByCourse(courseId))
            .thenReturn(FakeData.generateModules(courseId))
        val modules = viewModel.modules()
        verify(academyRepository).getAllModulesByCourse(courseId)
        assertNotNull(modules)
        assertEquals(7, modules.size)
    }
}