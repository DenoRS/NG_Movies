package ie.rubberduck.ngmovies.features.dashboard

import ie.rubberduck.domain.models.MovieModel

data class MovieUiModel(
    val title: String,
    val releaseDate: String,
    val posterPath: String,
    val overview: String,
    val voteAverage: Double

)