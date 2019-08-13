package id.mustofa.app.academy.data.source.remote

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

        fun instance(jsonHelper: JsonHelper): AcademyRemoteDataSource {
            synchronized(lock) {
                if (instance == null) {
                    instance = AcademyRemoteDataSource(jsonHelper)
                }
            }
            return instance!!
        }
    }

    fun getAllCourses() = jsonHelper.loadCourses()

    fun getModules(courseId: String) = jsonHelper.loadModules(courseId)

    fun getContent(moduleId: String) = jsonHelper.loadContent(moduleId)
}