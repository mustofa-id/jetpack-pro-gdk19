package id.mustofa.app.amber.data

/**
 * @author Habib Mustofa
 * Indonesia on 17/08/19
 */
object FakeMovieData {

    fun getMovies() = listOf(
        Movie(
            384018,
            "Fast & Furious Presents: Hobbs & Shaw",
            "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
            "2019-08-01",
            6.5F,
            listOf(Genre(28, "Action")),
            "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg",
            "/hpgda6P9GutvdkDX5MUJ92QG9aj.jpg"
        ),
        Movie(
            429203,
            "The Old Man & the Gun",
            "The true story of Forrest Tucker, from his audacious escape from San Quentin at the age of 70 to an unprecedented string of heists that confounded authorities and enchanted the public.  Wrapped up in the pursuit are a detective, who becomes captivated with Forrest’s commitment to his craft, and a woman, fullmazahd.in who loves him in spite of his chosen profession.",
            "2018-09-27",
            6.4F,
            listOf(Genre(35, "Comedy"), Genre(80, "Crime"), Genre(18, "Drama")),
            "/a4BfxRK8dBgbQqbRxPs8kmLd8LG.jpg",
            "/8bRIfPGDnmWgdy65LO8xtdcFmFP.jpg"
        ),
        Movie(
            420818,
            "The Lion King",
            "Simba idolises his father, King Mufasa, and takes to heart his own royal destiny. But not everyone in the kingdom celebrates the new cub's arrival. Scar, Mufasa's brother—and former heir to the throne—has plans of his own. The battle for Pride Rock is ravaged with betrayal, tragedy and drama, ultimately resulting in Simba's exile. With help from a curious pair of newfound friends, Simba will have to figure out how to grow up and take back what is rightfully his.",
            "2019-07-12",
            7.2F,
            listOf(
                Genre(12, "Adventure"),
                Genre(16, "Animation"),
                Genre(10751, "Family"),
                Genre(18, "Drama"),
                Genre(28, "Action")
            ),
            "/2bXbqYdUdNVa8VIWXVfclP2ICtT.jpg",
            "/1TUg5pO1VZ4B0Q1amk3OlXvlpXV.jpg"
        )
    )

    fun getTvshows() = listOf(
        Movie(
            60735,
            "The Flash",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            "2014-10-07",
            6.6F,
            listOf(),
            "/fki3kBlwJzFp8QohL43g9ReV455.jpg",
            "/jC1KqsFx8ZyqJyQa2Ohi7xgL7XC.jpg"
        ),
        Movie(
            62286,
            "Fear the Walking Dead",
            "What did the world look like as it was transforming into the horrifying apocalypse depicted in \\\"The Walking Dead\\\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.",
            "2015-08-23",
            6.3F,
            listOf(Genre(18, "Drama"), Genre(27, "Horror")),
            "/lZMb3R3e5vqukPbeDMeyYGf2ZNG.jpg",
            "/nUXzdD2Jo3wV9Q7mIZjK46Yyty4.jpg"
        ),
        Movie(
            1412,
            "Arrow",
            "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
            "2012-10-10",
            5.8F,
            listOf(
                Genre(80, "Crime"),
                Genre(18, "Drama"),
                Genre(9648, "Mystery"),
                Genre(10759, "Action & Adventure")
            ),
            "/mo0FP1GxOFZT4UDde7RFDz5APXF.jpg",
            "/dKxkwAJfGuznW8Hu0mhaDJtna0n.jpg"
        )
    )

}