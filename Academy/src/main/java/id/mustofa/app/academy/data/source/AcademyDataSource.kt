package id.mustofa.app.academy.data.source

import androidx.lifecycle.LiveData
import id.mustofa.app.academy.data.Course
import id.mustofa.app.academy.data.Module

/**
 * @author Habib Mustofa
 * Indonesia on 13/08/19
 */
interface AcademyDataSource {

    fun getAllCourses(): LiveData<List<Course>>

    fun getCourseWithModules(courseId: String): LiveData<Course?>

    fun getAllModulesByCourse(courseId: String): LiveData<List<Module>>

    fun getBookmarkedCourses(): LiveData<List<Course>>

    fun getContent(courseId: String, moduleId: String): LiveData<Module?>
}