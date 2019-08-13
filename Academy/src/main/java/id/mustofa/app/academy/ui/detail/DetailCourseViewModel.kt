package id.mustofa.app.academy.ui.detail

import androidx.lifecycle.ViewModel
import id.mustofa.app.academy.data.Course
import id.mustofa.app.academy.data.Module
import id.mustofa.app.academy.data.source.AcademyRepository

/**
 * @author Habib Mustofa
 * Indonesia on 04/08/19
 */
class DetailCourseViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    lateinit var courseId: String

    fun course(): Course {
        return academyRepository.getCourseWithModules(courseId)!!
    }

    fun modules(): List<Module> {
        return academyRepository.getAllModulesByCourse(courseId)
    }
}