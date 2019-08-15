package id.mustofa.app.academy.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.mustofa.app.academy.data.source.remote.AcademyRemoteDataSource
import id.mustofa.app.academy.util.FakeData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

/**
 * @author Habib Mustofa
 * Indonesia on 15/08/19
 */
class AcademyRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteDataSource = mock(AcademyRemoteDataSource::class.java)
    private val academyRepository = FakeAcademyRepository(remoteDataSource)

    private val courseResponse = FakeData.generateRemoteCourses()
    private val courseResponseId = courseResponse[0].id

    private val moduleResponse = FakeData.generateRemoteModules(courseResponseId)
    private val moduleResponseId = moduleResponse[0].moduleId
    private val contentResponse = FakeData.generateRemoteContent(moduleResponseId)

    @Test
    fun getAllCourses() {
        `when`(remoteDataSource.getAllCourses()).thenReturn(courseResponse)
        val courses = academyRepository.getAllCourses()
        verify(remoteDataSource).getAllCourses()
        assertNotNull(courses)
        assertEquals(courseResponse.size, courses.size)
    }

    @Test
    fun getAllModulesByCourse() {
        `when`(remoteDataSource.getAllCourses()).thenReturn(courseResponse)
        val bookmarkedCourses = academyRepository.getBookmarkedCourses()
        verify(remoteDataSource).getAllCourses()
        assertNotNull(bookmarkedCourses)
        assertEquals(courseResponse.size, bookmarkedCourses.size)
    }

    @Test
    fun getContent() {
        `when`(remoteDataSource.getModules(courseResponseId)).thenReturn(moduleResponse)
        `when`(remoteDataSource.getContent(moduleResponseId)).thenReturn(contentResponse)
        val module = academyRepository.getContent(courseResponseId, moduleResponseId)
        verify(remoteDataSource).getContent(moduleResponseId)
        assertNotNull(module)
        assertEquals(contentResponse.content, module?.content?.content)
    }

    @Test
    fun getCourseWithModules() {
        `when`(remoteDataSource.getAllCourses()).thenReturn(courseResponse)
        val course = academyRepository.getCourseWithModules(courseResponseId)
        verify(remoteDataSource).getAllCourses()
        assertNotNull(course)
        assertEquals(courseResponse[0].title, course?.title)
    }
}