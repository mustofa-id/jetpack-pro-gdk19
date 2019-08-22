package id.mustofa.app.amber.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import id.mustofa.app.amber.base.BaseModel
import id.mustofa.app.amber.util.MediaType

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
@Entity(tableName = "movie_favorite")
data class Movie(

    @PrimaryKey
    override var id: Long = -1,

    @SerializedName(value = "title", alternate = ["name"])
    var title: String = "",

    var overview: String = "",

    @SerializedName(value = "release_date", alternate = ["first_air_date"])
    var releaseDate: String = "",

    @SerializedName("vote_average")
    var voteAverage: Float = 0F,

    var genres: List<Genre> = emptyList(),

    @SerializedName("poster_path")
    var posterPath: String = "",

    @SerializedName("backdrop_path")
    var backdropPath: String = "",

    var mediaType: MediaType

) : BaseModel()