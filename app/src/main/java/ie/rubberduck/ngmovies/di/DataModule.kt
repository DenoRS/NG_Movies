package ie.rubberduck.ngmovies.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ie.rubberduck.data.remote.api.ApiConfig
import ie.rubberduck.data.remote.api.MovieApi
import ie.rubberduck.data.remote.auth.AuthInterceptor
import ie.rubberduck.data.remote.client.MovieClient
import ie.rubberduck.data.remote.client.MovieClientImpl
import ie.rubberduck.data.room.MovieDatabase
import ie.rubberduck.data.remote.qualifiers.TmdbAccessToken
import ie.rubberduck.data.remote.qualifiers.TmdbApiKey
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    // Fix if there is time left
    //    @TmdbApiKey
    //    @Provides
    //    fun provideTmdbApiKey(): String = BuildConfig.TMDB_API_KEY

    @TmdbAccessToken
    @Provides
    fun provideTmdbAccessToken(): String =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiMGQ4ODhjMDhiNDk5YjU2NGMwZjYzMDNjYTczMDVjZiIsIm5iZiI6MTc1ODgyMTkwMC40MjcsInN1YiI6IjY4ZDU3ZTBjZThjNDNhYjM1MWI3N2QzMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.hYnqRAmRZkbSjthZdZ3Gq32n3CEva6xSlmwAAUJRT0I"
    //BuildConfig.TMDB_ACCESS_TOKEN

    @Provides
    @Singleton
    fun provideAuthInterceptor(@TmdbAccessToken accessToken: String): Interceptor {
        return AuthInterceptor(accessToken = accessToken)
        // Use build config or preferably firebase to secure token if there is time
        // return AuthInterceptor(BuildConfig.TMDB_ACCESS_TOKEN)
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase =
        Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movie_database"
        ).build()

    @Provides
    @Singleton
    fun provideMovieDao(db: MovieDatabase) = db.movieDao()

    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)

    @Provides
    @Singleton
    fun provideMovieClient(api: MovieApi): MovieClient {
        return MovieClientImpl(api)
    }

}