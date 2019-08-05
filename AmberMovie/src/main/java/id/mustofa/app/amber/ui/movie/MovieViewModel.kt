package id.mustofa.app.amber.ui.movie

import androidx.lifecycle.ViewModel
import id.mustofa.app.amber.data.source.local.LocalRepository

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
class MovieViewModel: ViewModel() {

    private val repository = LocalRepository()

    fun getMovies() = repository.movies()
}