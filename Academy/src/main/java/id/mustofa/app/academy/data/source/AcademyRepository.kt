package id.mustofa.app.academy.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.mustofa.app.academy.data.Content
import id.mustofa.app.academy.data.Course
import id.mustofa.app.academy.data.Module
import id.mustofa.app.academy.data.source.local.AcademyLocalDataSource
import id.mustofa.app.academy.data.source.remote.AcademyRemoteDataSource

/**
 * @author Habib Mustofa
 * Indonesia on 13/08/19
 */
class AcademyRepository private constructor(
    private val localDataSource: AcademyLocalDataSource,
    private var remoteDataSource: AcademyRemoteDataSource
) : AcademyDataSource {

    companion object {

        @Volatile
        private var instance: AcademyRepository? = null
        private val lock = Any()

        fun instance(
            localDataSource: AcademyLocalDataSource,
            remoteDataSource: AcademyRemoteDataSource
        ) = instance ?: synchronized(lock) {
            instance ?: AcademyRepository(localDataSource, remoteDataSource)
        }
    }

    override fun getAllCourses(): LiveData<List<Course>> {
        val allCourses = MutableLiveData<List<Course>>()
        remoteDataSource.getAllCourses { result ->
            val data = result.map {
                Course(it.id, it.title, it.description, it.date, false, it.imagePath)
            }
            allCourses.postValue(data)
        }
        return allCourses
    }

    override fun getCourseWithModules(courseId: String): LiveData<Course?> {
        val coursesWithModule = MutableLiveData<Course?>()
        remoteDataSource.getAllCourses { result ->
            val data = result.firstOrNull { response -> response.id == courseId }
                ?.let {
                    Course(it.id, it.title, it.description, it.date, false, it.imagePath)
                }
            coursesWithModule.postValue(data)
        }
        return coursesWithModule
    }

    override fun getAllModulesByCourse(courseId: String): LiveData<List<Module>> {
        val modules = MutableLiveData<List<Module>>()
        remoteDataSource.getModules(courseId) { result ->
            val data = result.map {
                Module(it.moduleId, it.courseId, it.title, it.position, false)
            }
            modules.postValue(data)
        }
        return modules
    }

    override fun getBookmarkedCourses(): LiveData<List<Course>> {
        val bookmarks = MutableLiveData<List<Course>>()
        remoteDataSource.getAllCourses { result ->
            val data = result.map {
                Course(it.id, it.title, it.description, it.date, false, it.imagePath)
            }
            bookmarks.postValue(data)
        }
        return bookmarks
    }

    override fun getContent(courseId: String, moduleId: String): LiveData<Module?> {
        val contentVal = MutableLiveData<Module?>()
        remoteDataSource.getModules(courseId) { result ->
            result.firstOrNull { response -> response.moduleId == moduleId }
                ?.let {
                    val module = Module(it.moduleId, it.courseId, it.title, it.position, false)
                    remoteDataSource.getContent(moduleId) { contentRes ->
                        module.content = Content(contentRes.content)
                        contentVal.postValue(module)
                    }
                }
        }
        return contentVal
    }
}