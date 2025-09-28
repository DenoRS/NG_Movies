package ie.rubberduck.ngmovies.features.details

data class MovieDetailsUiModel(
    val title: String,
    val duration: Int,
    val releaseDate: String,
    val backdropPath: String?,
    val posterPath: String?,
    val overview: String,
    val voteAverage: Double,
    val genres: List<String>
)
