package id.mustofa.app.amber.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.mustofa.app.amber.data.Movie

/**
 * @author Habib Mustofa
 * Indonesia on 26/08/19
 */
abstract class ListMovieViewModel : ViewModel() {

    abstract val movies: LiveData<List<Movie>>
    abstract val loading: LiveData<Boolean>
    abstract val message: LiveData<Int>
    abstract val empty: LiveData<Boolean>

    abstract fun fetchMovies(force: Boolean = false)
}