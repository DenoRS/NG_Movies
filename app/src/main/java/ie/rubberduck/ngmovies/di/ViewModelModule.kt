package ie.rubberduck.ngmovies.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ie.rubberduck.domain.usecase.GetMovieDetailsUseCase
import ie.rubberduck.domain.usecase.GetPopularMoviesUseCase
import ie.rubberduck.domain.usecase.GetTopRatedMoviesUseCase
import ie.rubberduck.domain.usecase.SearchMoviesUseCase
import ie.rubberduck.ngmovies.features.dashboard.DashboardViewModel
import ie.rubberduck.ngmovies.features.details.DetailsViewModel
import ie.rubberduck.ngmovies.features.search.SearchViewModel

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideDashboardViewModel(
        getPopularMoviesUseCase: GetPopularMoviesUseCase,
        getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    ): DashboardViewModel {
        return DashboardViewModel(
            getPopularMoviesUseCase = getPopularMoviesUseCase,
            getTopRatedMoviesUseCase = getTopRatedMoviesUseCase,
        )
    }

    @Provides
    fun provideDetailsViewModel(
        getMovieDetailsUseCase: GetMovieDetailsUseCase,
    ): DetailsViewModel {
        return DetailsViewModel(
            getMovieDetailsUseCase = getMovieDetailsUseCase,
        )
    }

    @Provides
    fun provideSearchViewModel(
        searchMoviesUseCase: SearchMoviesUseCase,
    ): SearchViewModel {
        return SearchViewModel(
            searchMoviesUseCase = searchMoviesUseCase
        )
    }
}