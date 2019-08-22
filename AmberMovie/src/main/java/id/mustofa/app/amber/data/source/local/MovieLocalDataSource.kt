package id.mustofa.app.amber.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.util.MediaType

/**
 * @author Habib Mustofa
 * Indonesia on 21/08/19
 */
@Dao
interface MovieLocalDataSource {

    @Query("SELECT * FROM movie_favorite WHERE mediaType=:type")
    suspend fun getFavorites(type: MediaType): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorite(movie: Movie): Long

    @Query("DELETE FROM movie_favorite WHERE id=:movieId")
    suspend fun removeFromFavorite(movieId: Long): Int
}