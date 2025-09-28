package ie.rubberduck.domain.models

data class MovieDetailsModel(
    val id: Int,
    val title: String,
    val duration: Int,
    val releaseDate: String,
    val backdropPath: String?,
    val posterPath: String?,
    val overview: String,
    val voteAverage: Double,
    val genres: List<MovieGenresModel>
)

data class MovieGenresModel(
    val id: Int,
    val name: String
)
