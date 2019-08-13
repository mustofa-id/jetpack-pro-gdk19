package id.mustofa.app.academy.data.source.remote.response

/**
 * @author Habib Mustofa
 * Indonesia on 13/08/19
 */
data class ContentResponse(
    var moduleId: String,
    var content: String
)

data class CourseResponse(
    var id: String,
    var title: String,
    var description: String,
    var date: String,
    var imagePath: String
)

data class ModuleResponse(
    var moduleId: String,
    var courseId: String,
    var title: String,
    var position: Int
)