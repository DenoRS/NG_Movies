package ie.rubberduck.domain.models

data class MovieModel(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val posterPath: String,
    val overview: String,
    val voteAverage: Double
)