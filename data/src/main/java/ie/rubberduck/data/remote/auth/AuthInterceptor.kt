package ie.rubberduck.data.remote.auth

import ie.rubberduck.data.remote.qualifiers.TmdbAccessToken
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    @TmdbAccessToken private val accessToken: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .addHeader("accept", "application/json")
            .build()
        return chain.proceed(newRequest)
    }
}