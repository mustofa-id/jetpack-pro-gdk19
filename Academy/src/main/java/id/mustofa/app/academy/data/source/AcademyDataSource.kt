package id.mustofa.app.academy.data.source

import id.mustofa.app.academy.data.Course
import id.mustofa.app.academy.data.Module

/**
 * @author Habib Mustofa
 * Indonesia on 13/08/19
 */
interface AcademyDataSource {

    fun getAllCourses(): List<Course>

    fun getCourseWithModules(courseId: String): Course?

    fun getAllModulesByCourse(courseId: String): List<Module>

    fun getBookmarkedCourses(): List<Course>

    fun getContent(courseId: String, moduleId: String): Module?
}