package ie.rubberduck.data.room.converters

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import ie.rubberduck.data.room.entities.MovieGenresEntity

//@ProvidedTypeConverter // - look at provided type converter to use hilt injected moshi
class GenreTypeConverter() {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val type = Types.newParameterizedType(
        List::class.java,
        MovieGenresEntity::class.java
    )
    private val adapter = moshi.adapter<List<MovieGenresEntity>>(type)

    @TypeConverter
    fun fromGenres(genres: List<MovieGenresEntity>?): String =
        adapter.toJson(genres ?: emptyList())

    @TypeConverter
    fun toGenres(json: String?): List<MovieGenresEntity> =
        json?.let { adapter.fromJson(it) } ?: emptyList()
}