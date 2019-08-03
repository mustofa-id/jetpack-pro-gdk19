package id.mustofa.app.academy.data

/**
 * @author Habib Mustofa
 * Indonesia on 03/08/19
 */
data class Course(
    var courseId: String,
    var title: String,
    var description: String,
    var deadline: String,
    var bookmarked: Boolean = false,
    var imagePath: String
)