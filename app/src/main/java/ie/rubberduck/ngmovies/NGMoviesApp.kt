package ie.rubberduck.ngmovies

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Singleton

@HiltAndroidApp
@Singleton
class NGMoviesApp : Application()