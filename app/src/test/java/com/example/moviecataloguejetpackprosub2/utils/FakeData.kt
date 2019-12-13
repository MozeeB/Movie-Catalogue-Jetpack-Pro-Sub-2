package com.example.moviecataloguejetpackprosub2.utils

import com.example.moviecataloguejetpackprosub2.data.model.DetaiMovielModel
import com.example.moviecataloguejetpackprosub2.data.model.DetailTvShowModel
import com.example.moviecataloguejetpackprosub2.data.model.MoviesModel
import com.example.moviecataloguejetpackprosub2.data.model.TvShowModel
import com.example.moviecataloguejetpackprosub2.domain.MoviesDomain
import java.util.ArrayList

object FakeData {
    fun generateDummyMovies(): List<MoviesModel> {
        val movies: MutableList<MoviesModel> = ArrayList()
        movies.add(
            MoviesModel(
                "qdfARIhgpgZOBh3vfNhWS4hmSo3.jpg",
                330457,
                "/xJWPZIYOEFIjZpBL7SVBGnzRYXp.jpg",
                "Frozen II",
                7.1,
                "Elsa, Anna, Kristoff and Olaf head far into the forest to learn the truth about an ancient mystery of their kingdom.",
                "2019-11-20"
            )
        )
        movies.add(
            MoviesModel(
                "/mbm8k3GFhXS0ROd9AD1gqYbIFbM.jpg",
                398978,
                "/iLLDiO4dbUfFEnRug8DuvFEl1NB.jpg",
                "The Irishman",
                8.0,
                "Pennsylvania, 1956. Frank Sheeran, a war veteran of Irish origin who works as a truck driver, accidentally meets mobster Russell Bufalino. Once Frank becomes his trusted man, Bufalino sends him to Chicago with the task of helping Jimmy Hoffa, a powerful union leader related to organized crime, with whom Frank will maintain a close friendship for nearly twenty years.",
                "2019-11-01"
            )
        )
        return movies
    }

    fun getMovieDetail(): DetaiMovielModel? {
        return DetaiMovielModel(
            "/vZiqhw6oFoMlHSneIdVip9rRou2.jpg",
            "Frozen II",
            "Elsa, Anna, Kristoff and Olaf head far into the forest to learn the truth about an ancient mystery of their kingdom.",
            "/qdfARIhgpgZOBh3vfNhWS4hmSo3.jpg",
            "2019-11-20",
            7.1
        )
    }

    fun generateDummyTvShow(): List<TvShowModel>? {
        val tvShows: MutableList<TvShowModel> = ArrayList<TvShowModel>()
        tvShows.add(
            TvShowModel(
                "The Mandalorian",
                "2019-11-12",
                "/o7qi2v4uWQ8bZ1tW3KI0Ztn2epk.jpg",
                82856,
                7.6,
                "Set after the fall of the Empire and before the emergence of the First Order, we follow the travails of a lone gunfighter in the outer reaches of the galaxy far from the authority of the New Republic.",
                "/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg"
            )
        )
        tvShows.add(
            TvShowModel(
                "Rick and Morty",
                "2013-12-02",
                "/mzzHr6g1yvZ05Mc7hNj3tUdy2bM.jpg",
                60625,
                8.6,
                "Rick is a mentally-unbalanced but scientifically-gifted old man who has recently reconnected with his family. He spends most of his time involving his young grandson Morty in dangerous, outlandish adventures throughout space and alternate universes. Compounded with Morty's already unstable family life, these events cause Morty much distress at home and school.",
                "/qJdfO3ahgAMf2rcmhoqngjBBZW1.jpg"
            )
        )
        return tvShows
    }

    fun getTvShowsDetail(): DetailTvShowModel? {
        return DetailTvShowModel(
            "/o7qi2v4uWQ8bZ1tW3KI0Ztn2epk.jpg",
            "2019-11-12",
            "The Mandalorian",
            "Set after the fall of the Empire and before the emergence of the First Order, we follow the travails of a lone gunfighter in the outer reaches of the galaxy far from the authority of the New Republic.",
            "/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg",
            7.7
        )

    }

}