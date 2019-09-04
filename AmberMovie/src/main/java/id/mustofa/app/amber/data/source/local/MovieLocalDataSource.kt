package id.mustofa.app.amber.data.source.local

import androidx.paging.DataSource
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
    fun getFavorites(type: MediaType): DataSource.Factory<Int, Movie>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addToFavorite(movie: Movie): Long

    @Query("DELETE FROM movie_favorite WHERE id=:movieId")
    suspend fun removeFromFavorite(movieId: Long): Int

    @Query("SELECT count(id) FROM movie_favorite WHERE id=:id")
    suspend fun isInFavorite(id: Long): Int
}