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

    fun tvshows(): List<Movie> {
        return listOf(
            Movie(
                11111,
                "Arrow",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "October 10, 2012",
                5.79f,
                listOf("CRIME", "DRAMA", "MYSTERY", "ACTION & ADVENTURE"),
                R.drawable.poster_arrow
            )
        )
    }
}