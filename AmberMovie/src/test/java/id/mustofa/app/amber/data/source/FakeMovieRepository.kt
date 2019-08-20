package id.mustofa.app.amber.data.source

import id.mustofa.app.amber.R
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.data.Result
import id.mustofa.app.amber.data.Result.Error
import id.mustofa.app.amber.data.Result.Success
import id.mustofa.app.amber.data.source.remote.Movies

/**
 * @author Habib Mustofa
 * Indonesia on 19/08/19
 */
class FakeMovieRepository : MovieRepository {

    private val movieData = LinkedHashMap<Long, Movie>()

    var shouldReturnError = false

    fun addMovies(vararg movies: Movie) {
        movies.forEach { movieData[it.id] = it }
    }

    override suspend fun getAllMovies(): Result<Movies> {
        if (shouldReturnError) return Error(R.string.msg_something_wrong)
        return Success(Movies(movieData.values.toList()))
    }

    override suspend fun getAllTvshow(): Result<Movies> {
        if (shouldReturnError) return Error(R.string.msg_something_wrong)
        return Success(Movies(movieData.values.toList()))
    }

    override suspend fun getMovieById(id: Long): Result<Movie> {
        if (shouldReturnError) return Error(R.string.msg_something_wrong)
        movieData[id]?.let {
            return Success(it)
        }
        return Error(R.string.msg_not_found)
    }

    override suspend fun getTvshowById(id: Long): Result<Movie> {
        if (shouldReturnError) return Error(R.string.msg_something_wrong)
        movieData[id]?.let {
            return Success(it)
        }
        return Error(R.string.msg_not_found)
    }
}