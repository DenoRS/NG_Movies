package ie.rubberduck.data.remote.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TmdbApiKey

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TmdbAccessToken