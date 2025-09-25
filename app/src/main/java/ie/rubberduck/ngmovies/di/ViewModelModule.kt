package ie.rubberduck.ngmovies.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ie.rubberduck.domain.interactor.GetMoviesInteractor
import ie.rubberduck.ngmovies.features.dashboard.DashboardViewModel

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideDashboardViewModel(
        getMoviesInteractor: GetMoviesInteractor,
    ): DashboardViewModel {
        return DashboardViewModel(getMoviesInteractor)
    }
}