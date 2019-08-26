package id.mustofa.app.amber.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import id.mustofa.app.amber.base.ListMovieViewModel
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.data.Result
import id.mustofa.app.amber.data.source.MovieRepository
import kotlinx.coroutines.launch

/**
 * @author Habib Mustofa
 * Indonesia on 26/08/19
 */
class MovieFavoriteViewModel(private val movieRepository: MovieRepository) : ListMovieViewModel() {

    private val _movies = MutableLiveData<List<Movie>>().apply { value = emptyList() }
    override val movies = _movies

    private val _message = MutableLiveData<Int>()
    override val message = _message

    private val _loading = MutableLiveData<Boolean>()
    override val loading = _loading

    override val empty: LiveData<Boolean> = Transformations.map(_movies) { it.isEmpty() }

    init {
        fetchMovies()
    }

    override fun fetchMovies(force: Boolean) {
        if (force) _movies.value = emptyList()

        _loading.value = true
        viewModelScope.launch {
            when (val result = movieRepository.getMovieFavorites()) {
                is Result.Success -> _movies.postValue(result.data ?: emptyList())
                is Result.Error -> _message.postValue(result.message)
            }
            _loading.postValue(false)
        }
    }
}