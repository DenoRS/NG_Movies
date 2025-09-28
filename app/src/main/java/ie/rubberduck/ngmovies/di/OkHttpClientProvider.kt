package ie.rubberduck.ngmovies.di

import ie.rubberduck.data.remote.auth.AuthInterceptor
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OkHttpClientProvider @Inject constructor(
    private val authInterceptor: AuthInterceptor
) {
    fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }
}