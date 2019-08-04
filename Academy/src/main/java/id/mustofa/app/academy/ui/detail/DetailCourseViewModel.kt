package id.mustofa.app.academy.ui.detail

import androidx.lifecycle.ViewModel
import id.mustofa.app.academy.data.Course
import id.mustofa.app.academy.data.Module
import id.mustofa.app.academy.util.generateModules
import id.mustofa.app.academy.util.getCourse

/**
 * @author Habib Mustofa
 * Indonesia on 04/08/19
 */
class DetailCourseViewModel : ViewModel() {

    lateinit var courseId: String

    fun course(): Course {
        return getCourse(courseId)
    }

    fun modules(): List<Module> {
        return generateModules(courseId)
    }
}