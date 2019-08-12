package id.mustofa.app.amber.data.local

import id.mustofa.app.amber.R
import id.mustofa.app.amber.data.Movie

/**
 * @author Habib Mustofa
 * Indonesia on 07/08/19
 */
class FakeLocalRepository {

    fun movies(): List<Movie> {
        return listOf(
            Movie(
                11101,
                "Avenger Infinity War",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "April 27, 2018",
                8.3f,
                listOf("Adventure", "Action", "Fantasy"),
                R.drawable.poster_avengerinfinity
            )
        )
    }
}