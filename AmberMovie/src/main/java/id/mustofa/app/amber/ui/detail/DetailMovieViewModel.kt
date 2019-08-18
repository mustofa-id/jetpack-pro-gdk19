package id.mustofa.app.amber.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.data.Result.Error
import id.mustofa.app.amber.data.Result.Success
import id.mustofa.app.amber.data.source.MovieRepository
import id.mustofa.app.amber.util.MediaType
import kotlinx.coroutines.launch

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
class DetailMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    var type: MediaType = MediaType.MOVIE
    var movieId: Long = -1

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    private val _message = MutableLiveData<Int>()
    val message: LiveData<Int> get() = _message

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    init {
        // NOTE: load data following activity lifetime
        // So load function will not trigger every configuration change
        fetchMovie()
    }

    private fun fetchMovie() {
        _loading.postValue(true)
        viewModelScope.launch {
            // NOTE: Any coroutine launched in this scope is
            // automatically canceled if the ViewModel is cleared.
            val result = when (type) {
                MediaType.MOVIE -> movieRepository.getMovieById(movieId)
                MediaType.TV -> movieRepository.getTvshowById(movieId)
            }
            when (result) {
                is Success -> _movie.postValue(result.data)
                is Error -> _message.postValue(result.message)
            }
            _loading.postValue(false)
        }
    }
}
