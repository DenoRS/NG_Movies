package ie.rubberduck.ngmovies.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ie.rubberduck.domain.interactor.GetMoviesInteractor
import ie.rubberduck.domain.repository.MovieRepository

@Module
@InstallIn(ViewModelComponent::class)
object InteractorModule {

    @Provides
    fun provideGetMoviesInteractor(
        repository: MovieRepository,
    ): GetMoviesInteractor = GetMoviesInteractor(repository)

}