package ie.rubberduck.ngmovies.features.mappers

import ie.rubberduck.domain.models.MovieModel
import ie.rubberduck.ngmovies.features.dashboard.MovieUiModel

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