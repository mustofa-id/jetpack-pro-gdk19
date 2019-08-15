package id.mustofa.app.academy.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import id.mustofa.app.academy.data.source.remote.AcademyRemoteDataSource
import id.mustofa.app.academy.data.source.remote.response.ContentResponse
import id.mustofa.app.academy.data.source.remote.response.CourseResponse
import id.mustofa.app.academy.data.source.remote.response.ModuleResponse
import id.mustofa.app.academy.util.FakeData
import id.mustofa.app.academy.util.LiveDataTestUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

/**
 * @author Habib Mustofa
 * Indonesia on 15/08/19
 */
@Suppress("UNCHECKED_CAST")
class AcademyRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteDataSource: AcademyRemoteDataSource = mock()
    private val academyRepository = FakeAcademyRepository(remoteDataSource)

    private val courseResponse = FakeData.generateRemoteCourses()
    private val courseResponseId = courseResponse[0].id

    private val moduleResponse = FakeData.generateRemoteModules(courseResponseId)
    private val moduleResponseId = moduleResponse[0].moduleId
    private val contentResponse = FakeData.generateRemoteContent(moduleResponseId)

    @Test
    fun getAllCourses() {
        doAnswer {
            (it.arguments[0] as (List<CourseResponse>) -> Unit).invoke(courseResponse)
            null
        }.`when`(remoteDataSource).getAllCourses(any())

        val result = LiveDataTestUtil.getValue(academyRepository.getAllCourses())
        verify(remoteDataSource, times(1)).getAllCourses(any())

        assertNotNull(result)
        assertEquals(courseResponse.size, result.size)
    }

    @Test
    fun getAllModulesByCourse() {
        doAnswer {
            (it.arguments[1] as (List<ModuleResponse>) -> Unit).invoke(moduleResponse)
            null
        }.`when`(remoteDataSource).getModules(eq(courseResponseId), any())

        val result = LiveDataTestUtil.getValue(
            academyRepository.getAllModulesByCourse(courseResponseId)
        )
        verify(remoteDataSource, times(1)).getModules(eq(courseResponseId), any())

        assertNotNull(result)
        assertEquals(moduleResponse.size, result.size)
    }

    @Test
    fun getBookmarkedCourses() {
        doAnswer {
            (it.arguments[0] as (List<CourseResponse>) -> Unit).invoke(courseResponse)
            null
        }.`when`(remoteDataSource).getAllCourses(any())

        val result = LiveDataTestUtil.getValue(academyRepository.getBookmarkedCourses())
        verify(remoteDataSource, times(1)).getAllCourses(any())

        assertNotNull(result)
        assertEquals(courseResponse.size, result.size)
    }

    @Test
    fun getContent() {
        doAnswer {
            (it.arguments[1] as (List<ModuleResponse>) -> Unit).invoke(moduleResponse)
            null
        }.`when`(remoteDataSource).getModules(eq(courseResponseId), any())

        doAnswer {
            (it.arguments[1] as (ContentResponse) -> Unit).invoke(contentResponse)
            null
        }.`when`(remoteDataSource).getContent(eq(moduleResponseId), any())

        val result = LiveDataTestUtil.getValue(
            academyRepository.getContent(courseResponseId, moduleResponseId)
        )

        verify(remoteDataSource, times(1)).getModules(eq(courseResponseId), any())
        verify(remoteDataSource, times(1)).getContent(eq(moduleResponseId), any())

        assertNotNull(result)
        assertNotNull(result?.content)
        assertNotNull(result?.content?.content)
        assertEquals(contentResponse.content, result?.content?.content)
    }

    @Test
    fun getCourseWithModules() {
        doAnswer {
            (it.arguments[0] as (List<CourseResponse>) -> Unit).invoke(courseResponse)
            null
        }.`when`(remoteDataSource).getAllCourses(any())

        val result = LiveDataTestUtil.getValue(
            academyRepository.getCourseWithModules(courseResponseId)
        )
        verify(remoteDataSource, times(1)).getAllCourses(any())

        assertNotNull(result)
        assertNotNull(result?.title)
        assertEquals(courseResponse[0].title, result?.title)
    }
}