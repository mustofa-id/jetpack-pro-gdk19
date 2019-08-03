package id.mustofa.app.academy.data

/**
 * @author Habib Mustofa
 * Indonesia on 03/08/19
 */
data class Module(
    var moduleId: String,
    var courseId: String,
    var title: String,
    var position: Int,
    var read: Boolean = false,
    var content: Content? = null
)