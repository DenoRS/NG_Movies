package ie.rubberduck.ngmovies.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ie.rubberduck.domain.usecase.GetPopularMoviesUseCase
import ie.rubberduck.domain.usecase.GetTopRatedMoviesUseCase
import ie.rubberduck.ngmovies.features.dashboard.DashboardViewModel

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
}