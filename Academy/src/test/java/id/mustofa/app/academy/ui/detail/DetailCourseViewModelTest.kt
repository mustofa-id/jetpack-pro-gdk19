package id.mustofa.app.academy.ui.detail

import id.mustofa.app.academy.data.Course
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/**
 * @author Habib Mustofa
 * Indonesia on 04/08/19
 */
class DetailCourseViewModelTest {

    private lateinit var viewModel: DetailCourseViewModel
    private lateinit var course: Course

    @Before
    fun setup() {
        // viewModel = DetailCourseViewModel(academyRepository)
        course = Course(
            "a14",
            "Menjadi Android Developer Expert",
            "Dicoding sebagai satu-satunya Google Authorized Training Partner di Indonesia telah melalui proses penyusunan kurikulum secara komprehensif. Semua modul telah diverifikasi langsung oleh Google untuk memastikan bahwa materi yang diajarkan relevan dan sesuai dengan kebutuhan industri digital saat ini. Peserta akan belajar membangun aplikasi Android dengan materi Testing, Debugging, Application, Application UX, Fundamental Application Components, Persistent Data Storage, dan Enhanced System Integration.",
            "100 Hari",
            false,
            "https://www.dicoding.com/images/small/academy/menjadi_android_developer_expert_logo_070119140352.jpg"
        )
        viewModel.courseId = course.courseId
    }

    @Test
    fun course() {
        val vmCourse = viewModel.course()
        assertNotNull(vmCourse)
        assertEquals(course, vmCourse)
    }

    @Test
    fun modules() {
        val modules = viewModel.modules()
        assertNotNull(modules)
        assertEquals(7, modules.size)
    }
}