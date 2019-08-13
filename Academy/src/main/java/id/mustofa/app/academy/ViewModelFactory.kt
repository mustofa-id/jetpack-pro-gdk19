package id.mustofa.app.academy

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.mustofa.app.academy.data.source.AcademyRepository
import id.mustofa.app.academy.ui.academy.AcademyViewModel
import id.mustofa.app.academy.ui.bookmark.BookmarkViewModel
import id.mustofa.app.academy.ui.detail.DetailCourseViewModel
import id.mustofa.app.academy.ui.reader.CourseReaderViewModel
import id.mustofa.app.academy.util.Injection

/**
 * @author Habib Mustofa
 * Indonesia on 13/08/19
 */
class ViewModelFactory private constructor(
    private val academyRepository: AcademyRepository
) : ViewModelProvider.NewInstanceFactory() {

    companion object {

        @Volatile
        private var instance: ViewModelFactory? = null
        private val lock = Any()

        fun instance(app: Application) = instance ?: synchronized(lock) {
            instance ?: ViewModelFactory(Injection.provideRepository(app))
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = with(modelClass) {
        when {
            isAssignableFrom(AcademyViewModel::class.java) ->
                AcademyViewModel(academyRepository)
            isAssignableFrom(DetailCourseViewModel::class.java) ->
                DetailCourseViewModel(academyRepository)
            isAssignableFrom(BookmarkViewModel::class.java) ->
                BookmarkViewModel(academyRepository)
            isAssignableFrom(CourseReaderViewModel::class.java) ->
                CourseReaderViewModel(academyRepository)
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T

}