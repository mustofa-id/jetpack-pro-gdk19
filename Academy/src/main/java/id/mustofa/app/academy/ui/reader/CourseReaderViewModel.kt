package id.mustofa.app.academy.ui.reader

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.mustofa.app.academy.data.Module
import id.mustofa.app.academy.data.source.AcademyRepository

/**
 * @author Habib Mustofa
 * Indonesia on 04/08/19
 */
class CourseReaderViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    lateinit var courseId: String
    lateinit var moduleId: String

    fun modules(): LiveData<List<Module>> {
        return academyRepository.getAllModulesByCourse(courseId)
    }

    fun module(): LiveData<Module?> {
        return academyRepository.getContent(courseId, moduleId)
    }
}