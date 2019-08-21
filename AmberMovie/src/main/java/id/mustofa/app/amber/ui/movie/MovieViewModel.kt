package id.mustofa.app.amber.ui.movie

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

    // NOTE: avoid to expose mutable liveData
    private val _allMovies = MutableLiveData<List<Movie>>()
    val allMovies: LiveData<List<Movie>> get() = _allMovies

    private val _message = MutableLiveData<Int>()
    val message: LiveData<Int> get() = _message

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    val empty: LiveData<Boolean> = Transformations.map(_allMovies) { it.isEmpty() }

    init {
        // NOTE: load data following Fragment lifetime
        // So load function will not trigger every configuration change
        fetchAllMovies()
    }

    fun fetchAllMovies(force: Boolean = false) {
        if (force) _allMovies.value = listOf()

        _loading.postValue(true)
        viewModelScope.launch {
            // NOTE: Any coroutine launched in this scope is
            // automatically canceled if the ViewModel is cleared.
            when (val result = movieRepository.getAllMovies()) {
                is Success -> _allMovies.postValue(result.data?.results)
                is Error -> _message.postValue(result.message)
            }
            _loading.postValue(false)
        }
    }
}