package id.mustofa.app.amber.ui.detail

import androidx.lifecycle.*
import id.mustofa.app.amber.R
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

    private val _isFavorite = MutableLiveData<Boolean>()

    val favoriteIcon: LiveData<Int> = Transformations.map(_isFavorite) {
        if (it) R.drawable.ic_favorite else R.drawable.ic_not_favorite
    }

    init {
        fetchMovie()
    }

    fun fetchMovie() {
        _loading.value = true
        viewModelScope.launch {
            _isFavorite.postValue(movieRepository.isInFavorite(movieId))

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

    fun toggleFavorite() {
        _movie.value?.let {
            val favorite = _isFavorite.value ?: false
            _isFavorite.value = !favorite
            viewModelScope.launch {

                fun errorResult(message: Int) {
                    _message.postValue(message)
                    _isFavorite.postValue(favorite)
                }

                if (favorite) {
                    when (val res = movieRepository.removeMovieFromFavorite(it.id)) {
                        is Error -> errorResult(res.message)
                        is Success -> res.data?.let {
                            _message.postValue(
                                if (it > 0) R.string.msg_removed_favorite
                                else R.string.msg_failed_remove_favorite
                            )
                        }
                    }
                } else {
                    when (val res = movieRepository.addMovieToFavorite(it.apply {
                        mediaType = type
                    })) {
                        is Error -> errorResult(res.message)
                        is Success -> res.data?.let {
                            _message.postValue(
                                if (it > 0) R.string.msg_added_favorite
                                else R.string.msg_failed_add_favorite
                            )
                        }
                    }
                }
            }
        }
    }
}
