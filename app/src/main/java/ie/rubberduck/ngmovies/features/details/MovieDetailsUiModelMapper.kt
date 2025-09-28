package ie.rubberduck.ngmovies.features.details

import ie.rubberduck.domain.models.MovieDetailsModel

object MovieDetailsUiModelMapper {

    fun MovieDetailsModel.toUiModel(): MovieDetailsUiModel = MovieDetailsUiModel(
        title = title,
        duration = duration,
        releaseDate = releaseDate,
        backdropPath = backdropPath,
        posterPath = posterPath,
        overview = overview,
        voteAverage = voteAverage,
        genres = genres.map { it.name }
    )
}
