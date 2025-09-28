package ie.rubberduck.ngmovies.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ie.rubberduck.domain.repository.MovieRepository
import ie.rubberduck.domain.usecase.GetPopularMoviesUseCase
import ie.rubberduck.domain.usecase.GetTopRatedMoviesUseCase

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetPopularMoviesUseCase(
        repository: MovieRepository,
    ): GetPopularMoviesUseCase = GetPopularMoviesUseCase(repository)

    @Provides
    fun provideGetTopRatedMoviesUseCase(
        repository: MovieRepository,
    ): GetTopRatedMoviesUseCase = GetTopRatedMoviesUseCase(repository)

}