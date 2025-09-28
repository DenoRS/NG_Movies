package ie.rubberduck.ngmovies.features.dashboard

data class MovieUiModel(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val posterPath: String?,
    val overview: String,
    val voteAverage: Double
)