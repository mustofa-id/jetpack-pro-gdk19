package id.mustofa.app.academy.ui.bookmark

import androidx.lifecycle.ViewModel
import id.mustofa.app.academy.data.Course
import id.mustofa.app.academy.util.generateCourses

/**
 * @author Habib Mustofa
 * Indonesia on 04/08/19
 */
class BookmarkViewModel : ViewModel() {

    fun bookmarks(): List<Course> {
        return generateCourses()
    }
}