package id.mustofa.app.academy.ui.reader

import id.mustofa.app.academy.data.Content
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/**
 * @author Habib Mustofa
 * Indonesia on 04/08/19
 */
class CourseReaderViewModelTest {

    private lateinit var viewModel: CourseReaderViewModel
    private lateinit var content: Content
    private lateinit var moduleId: String

    @Before
    fun setup() {
        viewModel = CourseReaderViewModel()
        viewModel.courseId = "a14"

        moduleId = "a14sm1"

        val title = viewModel.modules()[0].title

        content =
            Content("<h3 class=\\\"fr-text-bordered\\\">$title</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>")
    }

    @Test
    fun modules() {
        val modules = viewModel.modules()
        assertNotNull(modules)
        assertEquals(7, modules.size)
    }

    @Test
    fun module() {
        viewModel.moduleId = moduleId
        val module = viewModel.module()
        assertNotNull(module)

        val lContent = module.content
        assertNotNull(lContent)

        val contentValue = lContent?.content
        assertNotNull(contentValue)

        assertEquals(contentValue, content.content)
    }
}