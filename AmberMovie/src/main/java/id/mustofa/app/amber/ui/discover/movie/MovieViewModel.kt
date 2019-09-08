package id.mustofa.app.amber.ui.discover.movie

import androidx.lifecycle.*
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.data.Result.Error
import id.mustofa.app.amber.data.Result.Success
import id.mustofa.app.amber.data.source.MovieRepository
import kotlinx.coroutines.launch

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _allMovies = MutableLiveData<List<Movie>>().apply { value = emptyList() }
    val movies = _allMovies

    private val _message = MutableLiveData<Int>()
    val message = _message

    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading

    val empty: LiveData<Boolean> = Transformations.map(_allMovies) { it.isEmpty() }

    init {
        fetchMovies()
    }

    fun fetchMovies(force: Boolean = false) {
        if (force) _allMovies.value = listOf()

        _loading.postValue(true)
        viewModelScope.launch {
            when (val result = movieRepository.getAllMovies()) {
                is Success -> _allMovies.postValue(result.data?.results)
                is Error -> _message.postValue(result.message)
            }
            _loading.postValue(false)
        }
    }
}