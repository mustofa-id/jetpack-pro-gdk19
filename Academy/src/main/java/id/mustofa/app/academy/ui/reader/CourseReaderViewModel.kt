package id.mustofa.app.academy.ui.reader

import androidx.lifecycle.ViewModel
import id.mustofa.app.academy.data.Content
import id.mustofa.app.academy.data.Module
import id.mustofa.app.academy.util.generateModules

/**
 * @author Habib Mustofa
 * Indonesia on 04/08/19
 */
class CourseReaderViewModel : ViewModel() {

    lateinit var courseId: String
    lateinit var moduleId: String

    fun modules(): List<Module> {
        return generateModules(courseId)
    }

    fun module(): Module {
        return modules().first { it.moduleId == moduleId }.apply {
            content =
                Content("<h3 class=\\\"fr-text-bordered\\\">$title</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>")
        }
    }
}