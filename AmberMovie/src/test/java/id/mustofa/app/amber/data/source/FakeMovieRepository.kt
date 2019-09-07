package id.mustofa.app.amber.data.source

import id.mustofa.app.amber.R
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.data.Result
import id.mustofa.app.amber.data.Result.Error
import id.mustofa.app.amber.data.Result.Success
import id.mustofa.app.amber.data.source.remote.Movies
import id.mustofa.app.amber.mockDataSourceFactory
import id.mustofa.app.amber.util.MediaType

/**
 * @author Habib Mustofa
 * Indonesia on 19/08/19
 */
class FakeMovieRepository : MovieRepository {

    private val movieNetworkData = LinkedHashMap<Long, Movie>()
    private val movieLocalData = LinkedHashMap<Long, Movie>()

    var shouldReturnError = false

    fun addNetworkMovies(vararg movies: Movie) {
        movies.forEach { movieNetworkData[it.id] = it }
    }

    fun addLocalMovies(vararg movies: Movie) {
        movies.forEach { movieLocalData[it.id] = it }
    }

    override suspend fun getAllMovies() = withShouldError {
        Success(Movies(movieNetworkData.values.toList()))
    }

    override suspend fun getAllTvshow() = withShouldError {
        Success(Movies(movieNetworkData.values.toList()))
    }

    override suspend fun getMovieById(id: Long) = withShouldError {
        val result = movieNetworkData[id]
        if (result == null) Error(R.string.msg_not_found) else Success(result)
    }

    override suspend fun getTvshowById(id: Long) = withShouldError {
        val result = movieNetworkData[id]
        if (result == null) Error(R.string.msg_not_found) else Success(result)
    }

    override suspend fun getMovieFavorites() = withShouldError {
        val list = movieLocalData.values
            .filter { it.mediaType == MediaType.MOVIE }
            .toList()
        Success(mockDataSourceFactory(list))
    }

    override suspend fun getTvshowFavorites() = withShouldError {
        val list = movieLocalData.values
            .filter { it.mediaType == MediaType.TV }
            .toList()
        Success(mockDataSourceFactory(list))
    }

    override suspend fun getFavoriteById(id: Long) = withShouldError {
        val movie = movieLocalData.values.firstOrNull { it.id == id }
        if (movie == null) Error(R.string.msg_not_found) else Success(movie)
    }

    override suspend fun addMovieToFavorite(movie: Movie) = withShouldError {
        if (movieLocalData.put(movie.id, movie) != null) Success(movie.id)
        else Error(R.string.msg_failed_add_favorite)
    }

    override suspend fun removeMovieFromFavorite(movieId: Long) = withShouldError {
        if (movieLocalData.remove(movieId)?.id == movieId) Success(1) else Success(0)
    }

    override suspend fun isInFavorite(id: Long) = movieLocalData.containsKey(id)

    private inline fun <T> withShouldError(block: () -> Result<T>): Result<T> {
        return if (shouldReturnError) Error(R.string.msg_something_wrong) else block()
    }
}