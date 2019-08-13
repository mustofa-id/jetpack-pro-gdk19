package id.mustofa.app.academy.ui.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/**
 * @author Habib Mustofa
 * Indonesia on 04/08/19
 */
class AcademyViewModelTest {

    private lateinit var viewModel: AcademyViewModel

    @Before
    fun setup() {
        // viewModel = AcademyViewModel(academyRepository)
    }

    @Test
    fun courses() {
        val courses = viewModel.courses()
        assertNotNull(courses)
        assertEquals(5, courses.size)
    }
}