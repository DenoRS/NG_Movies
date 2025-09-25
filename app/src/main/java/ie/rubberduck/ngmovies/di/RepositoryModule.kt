package ie.rubberduck.ngmovies.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ie.rubberduck.data.repository.MovieRepositoryImpl
import ie.rubberduck.domain.repository.MovieRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository
}