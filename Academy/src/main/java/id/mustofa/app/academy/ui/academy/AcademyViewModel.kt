package id.mustofa.app.academy.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.mustofa.app.academy.data.Course
import id.mustofa.app.academy.data.source.AcademyRepository

/**
 * @author Habib Mustofa
 * Indonesia on 04/08/19
 */
class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun courses(): LiveData<List<Course>> {
        return academyRepository.getAllCourses()
    }
}