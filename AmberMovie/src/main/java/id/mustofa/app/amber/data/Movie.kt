package id.mustofa.app.amber.data

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import id.mustofa.app.amber.util.MediaType

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
@Entity(tableName = "movie_favorite")
data class Movie(

    @PrimaryKey
    val id: Long = -1,

    @SerializedName(value = "title", alternate = ["name"])
    val title: String = "",

    val overview: String = "",

    @SerializedName(value = "release_date", alternate = ["first_air_date"])
    val releaseDate: String = "",

    @SerializedName("vote_average")
    val voteAverage: Float = 0F,

    val genres: List<Genre> = emptyList(),

    @SerializedName("poster_path")
    val posterPath: String = "",

    @SerializedName("backdrop_path")
    val backdropPath: String = "",

    var mediaType: MediaType = MediaType.MOVIE

) {

    object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(o: Movie, n: Movie) = o.id == n.id
        override fun areContentsTheSame(o: Movie, n: Movie) = o == n
    }
}