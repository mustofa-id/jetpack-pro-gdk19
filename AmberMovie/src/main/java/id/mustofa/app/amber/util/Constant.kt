package id.mustofa.app.amber.util

import id.mustofa.app.amber.BuildConfig

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
object Const {

    const val EXTRA_MOVIE_ID = "__extraMovieId__"
    const val EXTRA_MOVIE_TYPE = "__extraMovieType__"

    const val TMDB_API_KEY = "api_key=${BuildConfig.MOVIEDB_API_KEY}"
}

enum class MediaType(private val value: String) {

    MOVIE("movie"), TV("tv");

    override fun toString() = value
}