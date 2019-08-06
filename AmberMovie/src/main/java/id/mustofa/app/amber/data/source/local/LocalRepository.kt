package id.mustofa.app.amber.data.source.local

import id.mustofa.app.amber.R
import id.mustofa.app.amber.data.Movie

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
class LocalRepository {

    fun movie(id: Long) = movies().first { it.id == id }

    fun tvshow(id: Long) = tvshows().first { it.id == id }

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
            ),
            Movie(
                11102,
                "Aquaman",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "December 7, 2018",
                6.8f,
                listOf("ACTION", "ADVENTURE", "FANTASY"),
                R.drawable.poster_aquaman
            ),
            Movie(
                11103,
                "Bumblebee",
                "On the run in the year 1987, Bumblebee finds refuge in a junkyard in a small Californian beach town. Charlie, on the cusp of turning 18 and trying to find her place in the world, discovers Bumblebee, battle-scarred and broken. When Charlie revives him, she quickly learns this is no ordinary yellow VW bug.",
                "December 15, 2018",
                6.5f,
                listOf("ACTION", "ADVENTURE", "SCIENCE FICTION"),
                R.drawable.poster_bumblebee
            ),
            Movie(
                11104,
                "How to Train Your Dragon: The Hidden World",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "January 3, 2019",
                7.7f,
                listOf("ANIMATION", "FAMILY", "ADVENTURE"),
                R.drawable.poster_dragon
            ),
            Movie(
                11105,
                "Bird Box",
                "Five years after an ominous unseen presence drives most of society to suicide, a survivor and her two children make a desperate bid to reach safety.",
                "December 13, 2018",
                7.0f,
                listOf("THRILLER", "DRAMA"),
                R.drawable.poster_birdbox
            ),
            Movie(
                11106,
                "Once Upon a Deadpool",
                "A kidnapped Fred Savage is forced to endure Deadpool's PG-13 rendition of Deadpool 2 as a Princess Bride-esque story that's full of magic, wonder & zero F's.",
                "December 11, 2018",
                7.0f,
                listOf("COMEDY", "ACTION"),
                R.drawable.poster_deadpool
            ),
            Movie(
                11107,
                "Spider-Man: Into the Spider-Verse",
                "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                "December 6, 2018",
                8.4f,
                listOf("ACTION", "ADVENTURE", "ANIMATION", "SCIENCE FICTION", "COMEDY"),
                R.drawable.poster_spiderman
            ),
            Movie(
                11108,
                "Dragon Ball Super: Broly",
                "Earth is peaceful following the Tournament of Power. Realizing that the universes still hold many more strong people yet to see, Goku spends all his days training to reach even greater heights. Then one day, Goku and Vegeta are faced by a Saiyan called 'Broly' who they've never seen before. The Saiyans were supposed to have been almost completely wiped out in the destruction of Planet Vegeta, so what's this one doing on Earth? This encounter between the three Saiyans who have followed completely different destinies turns into a stupendous battle, with even Frieza (back from Hell) getting caught up in the mix.",
                "December 14, 2018",
                7.4f,
                listOf("ACTION", "ANIMATION", "FANTASY", "ADVENTURE", "COMEDY", "SCIENCE FICTION"),
                R.drawable.poster_dragonball
            ),
            Movie(
                11109,
                "Mortal Engines",
                "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
                "November 27, 2018",
                6.0f,
                listOf("ADVENTURE", "FANTASY"),
                R.drawable.poster_mortalengine
            ),
            Movie(
                11110,
                "Venom",
                "Investigative journalist Eddie Brock attempts a comeback following a scandal, but accidentally becomes the host of Venom, a violent, super powerful alien symbiote. Soon, he must rely on his newfound powers to protect the world from a shadowy organization looking for a symbiote of their own.",
                "September 28, 2018",
                6.6f,
                listOf("SCIENCE FICTION", "ACTION"),
                R.drawable.poster_venom
            )
            // TV Show

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
            ),
            Movie(
                11112,
                "Shameless",
                "Chicagoan Frank Gallagher is the proud single dad of six smart, industrious, independent kids, who without him would be... perhaps better off. When Frank's not at the bar spending what little money they have, he's passed out on the floor. But the kids have found ways to grow up in spite of him. They may not be like any family you know, but they make no apologies for being exactly who they are.",
                "January 9, 2011",
                7.83f,
                listOf("DRAMA", "COMEDY"),
                R.drawable.poster_shameless
            ),
            Movie(
                11113,
                "Marvel's Iron Fist",
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                "March 17, 2017",
                6.06f,
                listOf("ACTION & ADVENTURE", "CRIME", "DRAMA", "SCI-FI & FANTASY"),
                R.drawable.poster_iron_fist
            ),
            Movie(
                11114,
                "Hanna",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "March 28, 2019",
                6.37f,
                listOf("ACTION & ADVENTURE", "DRAMA"),
                R.drawable.poster_hanna
            ),
            Movie(
                11115,
                "Gotham",
                "Before there was Batman, there was GOTHAM. \\n\\nEveryone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "September 22, 2014",
                6.85f,
                listOf("DRAMA", "FANTASY", "CRIME"),
                R.drawable.poster_gotham
            ),
            Movie(
                11116,
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "October 7, 2014",
                6.66f,
                listOf("DRAMA", "SCI-FI & FANTASY"),
                R.drawable.poster_flash
            ),
            Movie(
                11117,
                "The Umbrella Academy",
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                "February 15, 2019",
                7.65f,
                listOf("ACTION & ADVENTURE", "SCI-FI & FANTASY"),
                R.drawable.poster_the_umbrella
            ),
            Movie(
                11118,
                "Supergirl",
                "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                "October 26, 2015",
                5.77f,
                listOf("ACTION", "ADVENTURE", "DRAMA", "SCIENCE FICTION"),
                R.drawable.poster_supergirl
            ),
            Movie(
                11119,
                "Doom Patrol",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "February 15, 2019",
                6.12f,
                listOf("SCI-FI & FANTASY", "ACTION & ADVENTURE"),
                R.drawable.poster_doom_patrol
            ),
            Movie(
                11120,
                "Naruto Shippūden",
                "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
                "February 15, 2007",
                7.48f,
                listOf("ANIMATION", "COMEDY", "DRAMA"),
                R.drawable.poster_naruto_shipudden
            )
        )
    }
}