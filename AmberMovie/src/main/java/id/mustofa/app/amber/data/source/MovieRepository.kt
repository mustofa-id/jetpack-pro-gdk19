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
}