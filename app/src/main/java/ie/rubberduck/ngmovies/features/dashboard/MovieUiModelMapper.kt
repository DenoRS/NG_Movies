package ie.rubberduck.ngmovies.features.dashboard

import ie.rubberduck.domain.models.MovieModel

object MovieUiModelMapper {

    fun MovieModel.toUiModel(): MovieUiModel = MovieUiModel(
        id = id,
        title = title,
        releaseDate = releaseDate,
        posterPath = posterPath,
        overview = overview,
        voteAverage = voteAverage
    )
}
