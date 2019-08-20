package id.mustofa.app.amber.data

/**
 * @author Habib Mustofa
 * Indonesia on 17/08/19
 */
object FakeAndroidMovieData {

    fun getMovie() = Movie(
        384018,
        "Fast & Furious Presents: Hobbs & Shaw",
        "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
        "2019-08-01",
        6.5F,
        listOf(Genre(28, "Action")),
        "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg",
        "/hpgda6P9GutvdkDX5MUJ92QG9aj.jpg"
    )
}