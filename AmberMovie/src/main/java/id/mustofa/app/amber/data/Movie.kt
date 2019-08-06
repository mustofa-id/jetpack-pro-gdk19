package id.mustofa.app.amber.data

import androidx.annotation.DrawableRes
import id.mustofa.app.amber.base.BaseModel

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
data class Movie(
    var id: Long,
    var title: String,
    var overview: String,
    var releaseDate: String,
    var voteAverage: Float,
    var genres: List<String>,
    @DrawableRes var posterResId: Int
) : BaseModel(id)