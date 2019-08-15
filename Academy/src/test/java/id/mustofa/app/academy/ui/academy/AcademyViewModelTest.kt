package id.mustofa.app.academy.ui.academy

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
class AcademyViewModelTest {

    private lateinit var viewModel: AcademyViewModel
    private var academyRepository = mock(AcademyRepository::class.java)

    @Before
    fun setup() {
        viewModel = AcademyViewModel(academyRepository)
    }

    @Test
    fun courses() {
        `when`(academyRepository.getAllCourses()).thenReturn(FakeData.generateCourses())
        val courses = viewModel.courses()
        verify(academyRepository).getAllCourses()
        assertNotNull(courses)
        assertEquals(5, courses.size)
    }
}