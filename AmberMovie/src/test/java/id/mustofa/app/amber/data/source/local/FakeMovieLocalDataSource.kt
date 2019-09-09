package id.mustofa.app.amber.data.source.local

import androidx.paging.DataSource
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.mockDataSourceFactory
import id.mustofa.app.amber.util.MediaType

/**
 * @author Habib Mustofa
 * Indonesia on 05/09/19
 */
class FakeMovieLocalDataSource(private val movies: MutableList<Movie>) : MovieLocalDataSource {

    override fun getFavorites(type: MediaType): DataSource.Factory<Int, Movie> {
        return mockDataSourceFactory(movies.filter { it.mediaType == type })
    }

    override suspend fun getFavoriteById(id: Long): Movie {
        return movies.first { it.id == id }
    }

    override suspend fun addToFavorite(movie: Movie): Long {
        return if (movies.add(movie)) movie.id else -1
    }

    override suspend fun removeFromFavorite(movieId: Long): Int {
        return if (movies.removeIf { it.id == movieId }) 1 else -1
    }

    override suspend fun isInFavorite(id: Long): Int {
        return if (movies.firstOrNull { it.id == id } != null) 1 else 0
    }

    override fun countFavoriteByType(type: MediaType): Int {
        TODO("not implemented")
    }
}