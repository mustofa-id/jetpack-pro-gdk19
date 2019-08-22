package id.mustofa.app.amber.data.source

import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.data.Result
import id.mustofa.app.amber.data.source.remote.Movies

/**
 * @author Habib Mustofa
 * Indonesia on 16/08/19
 */
interface MovieRepository {

    suspend fun getAllMovies(): Result<Movies>

    suspend fun getAllTvshow(): Result<Movies>

    suspend fun getMovieById(id: Long): Result<Movie>

    suspend fun getTvshowById(id: Long): Result<Movie>

    suspend fun getMovieFavorites(): Result<List<Movie>>

    suspend fun getTvshowFavorites(): Result<List<Movie>>

    suspend fun addMovieToFavorite(movie: Movie): Result<Long>

    suspend fun removeMovieFromFavorite(movieId: Long): Result<Int>
}