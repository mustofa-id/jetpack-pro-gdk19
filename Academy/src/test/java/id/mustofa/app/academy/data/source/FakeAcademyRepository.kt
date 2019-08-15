package id.mustofa.app.academy.data.source

import id.mustofa.app.academy.data.Content
import id.mustofa.app.academy.data.Course
import id.mustofa.app.academy.data.Module
import id.mustofa.app.academy.data.source.remote.AcademyRemoteDataSource

/**
 * @author Habib Mustofa
 * Indonesia on 15/08/19
 */
class FakeAcademyRepository(
    private var remoteDataSource: AcademyRemoteDataSource
) : AcademyDataSource {

    override fun getAllCourses(): List<Course> {
        return remoteDataSource.getAllCourses().map {
            Course(it.id, it.title, it.description, it.date, false, it.imagePath)
        }
    }

    override fun getCourseWithModules(courseId: String): Course? {
        return remoteDataSource.getAllCourses()
            .firstOrNull { response -> response.id == courseId }
            ?.let {
                Course(it.id, it.title, it.description, it.date, false, it.imagePath)
            }
    }

    override fun getAllModulesByCourse(courseId: String): List<Module> {
        return remoteDataSource.getModules(courseId).map {
            Module(it.moduleId, it.courseId, it.title, it.position, false)
        }
    }

    override fun getBookmarkedCourses(): List<Course> {
        return remoteDataSource.getAllCourses().map {
            Course(it.id, it.title, it.description, it.date, false, it.imagePath)
        }
    }

    override fun getContent(courseId: String, moduleId: String): Module? {
        return remoteDataSource.getModules(courseId)
            .firstOrNull { response -> response.moduleId == moduleId }
            ?.let {
                val content = remoteDataSource.getContent(moduleId)
                Module(
                    it.moduleId, it.courseId, it.title,
                    it.position, false, Content(content.content)
                )
            }
    }
}