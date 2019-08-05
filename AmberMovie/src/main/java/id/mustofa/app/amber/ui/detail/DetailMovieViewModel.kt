package id.mustofa.app.amber.ui.detail

import androidx.lifecycle.ViewModel
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.data.source.local.LocalRepository
import id.mustofa.app.amber.util.MediaType

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
class DetailMovieViewModel : ViewModel() {

    private val repository = LocalRepository()

    var type: MediaType = MediaType.MOVIE
    var movieId: Long = -1

    fun getMovie(): Movie {
        return when (type) {
            MediaType.MOVIE -> repository.movie(movieId)
            MediaType.TV -> repository.tvshow(movieId)
        }
    }

}

