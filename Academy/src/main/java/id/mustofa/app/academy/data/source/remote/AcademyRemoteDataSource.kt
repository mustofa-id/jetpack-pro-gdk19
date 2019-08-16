package id.mustofa.app.academy.data.source.remote

import android.os.Handler
import id.mustofa.app.academy.data.source.remote.response.ContentResponse
import id.mustofa.app.academy.data.source.remote.response.CourseResponse
import id.mustofa.app.academy.data.source.remote.response.ModuleResponse
import id.mustofa.app.academy.util.EspressoIdlingResource

/**
 * @author Habib Mustofa
 * Indonesia on 13/08/19
 */
class AcademyRemoteDataSource private constructor(
    private val jsonHelper: JsonHelper
) {

    companion object {

        private var instance: AcademyRemoteDataSource? = null
        private val lock = Any()

        // TODO: Remove all EspressoIdlingResource implementation in production to avoid memory leaks
        fun instance(jsonHelper: JsonHelper): AcademyRemoteDataSource {
            synchronized(lock) {
                if (instance == null) {
                    instance = AcademyRemoteDataSource(jsonHelper)
                }
            }
            return instance!!
        }
    }

    fun getAllCourses(result: (List<CourseResponse>) -> Unit) {
        EspressoIdlingResource.increment()
        Handler().postDelayed({
            result(jsonHelper.loadCourses())
            EspressoIdlingResource.decrement()
        }, 2000)
    }

    fun getModules(courseId: String, result: (List<ModuleResponse>) -> Unit) {
        EspressoIdlingResource.increment()
        Handler().postDelayed({
            result(jsonHelper.loadModules(courseId))
            EspressoIdlingResource.decrement()
        }, 2000)
    }

    fun getContent(moduleId: String, result: (ContentResponse) -> Unit) {
        EspressoIdlingResource.increment()
        Handler().postDelayed({
            result(jsonHelper.loadContent(moduleId))
            EspressoIdlingResource.decrement()
        }, 2000)
    }
}