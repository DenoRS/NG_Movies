package ie.rubberduck.ngmovies.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module(
    includes = [
        DataModule::class,
        RepositoryModule::class,
        InteractorModule::class,
        ViewModelModule::class,
    ]
)
@InstallIn(SingletonComponent::class)
abstract class AppModule