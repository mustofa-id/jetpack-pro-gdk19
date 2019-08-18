package id.mustofa.app.amber.data

import com.google.gson.annotations.SerializedName
import id.mustofa.app.amber.base.BaseModel

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
data class Movie(

    override var id: Long,

    @SerializedName(value = "title", alternate = ["name"])
    var title: String,

    var overview: String,

    @SerializedName(value = "release_date", alternate = ["first_air_date"])
    var releaseDate: String,

    @SerializedName("vote_average")
    var voteAverage: Float,

    var genres: List<Genre> = listOf(),

    @SerializedName("poster_path")
    var posterPath: String,

    @SerializedName("backdrop_path")
    var backdropPath: String

) : BaseModel()