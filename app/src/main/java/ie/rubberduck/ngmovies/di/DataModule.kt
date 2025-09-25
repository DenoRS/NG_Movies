package ie.rubberduck.ngmovies.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ie.rubberduck.data.remote.api.MovieApi
import ie.rubberduck.data.remote.client.MovieClient
import ie.rubberduck.data.remote.client.MovieClientImpl
import ie.rubberduck.data.room.MovieDatabase
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return MovieDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun db(@ApplicationContext context: Context): MovieDatabase =
        Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movie_database"
        ).build()

    @Provides
    @Singleton
    fun movieDao(db: MovieDatabase) = db.movieDao()

    @Provides
    @Singleton
    fun provideMovieClient(api: MovieApi): MovieClient {
        return MovieClientImpl(api)
    }

    // Add url later after dashboard screen is created
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://someUrl")
            // maybe use GsonConverterFactory here?
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)



}