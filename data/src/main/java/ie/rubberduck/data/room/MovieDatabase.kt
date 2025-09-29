package ie.rubberduck.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ie.rubberduck.data.room.converters.GenreTypeConverter
import ie.rubberduck.data.room.dao.MovieDao
import ie.rubberduck.data.room.entities.MovieDetailsEntity
import ie.rubberduck.data.room.entities.MovieEntity
import ie.rubberduck.data.room.entities.MovieGenresEntity

@Database(
    entities = [
        MovieEntity::class,
        MovieDetailsEntity::class,
        MovieGenresEntity::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(GenreTypeConverter::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}