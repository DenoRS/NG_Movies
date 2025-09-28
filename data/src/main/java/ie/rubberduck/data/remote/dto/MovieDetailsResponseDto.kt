package ie.rubberduck.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ie.rubberduck.domain.models.MovieDetailsModel
import ie.rubberduck.domain.models.MovieGenresModel

@JsonClass(generateAdapter = true)
data class MovieDetailsResponseDto(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "runtime") val duration: Int,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "overview") val overview: String,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "genres") val genres: List<MovieGenresDto>
)

@JsonClass(generateAdapter = true)
data class MovieGenresDto(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String
)

fun MovieDetailsResponseDto.toDomain() = MovieDetailsModel(
    id = id,
    title = title,
    duration = duration,
    releaseDate = releaseDate,
    backdropPath = backdropPath,
    posterPath = posterPath,
    overview = overview,
    voteAverage = voteAverage,
    genres = genres.map { it.toDomain() }
)

fun MovieGenresDto.toDomain() = MovieGenresModel(
    id = id,
    name = name
)


//{
//  "adult": false,
//  "backdrop_path": "/fONtQBk12LGjQo4q6bXDROVQmTy.jpg",
//  "belongs_to_collection": null,
//  "budget": 4000000,
//  "genres": [
//    {
//      "id": 53,
//      "name": "Thriller"
//    },
//    {
//      "id": 12,
//      "name": "Adventure"
//    }
//  ],
//  "homepage": "",
//  "id": 213,
//  "imdb_id": "tt0053125",
//  "origin_country": [
//    "US"
//  ],
//  "original_language": "en",
//  "original_title": "North by Northwest",
//  "overview": "Advertising man Roger Thornhill is mistaken for a spy, triggering a deadly cross-country chase.",
//  "popularity": 4.39,
//  "poster_path": "/kNOFPQrel9YFCVzI0DF8FnCEpCw.jpg",
//  "production_companies": [
//    {
//      "id": 21,
//      "logo_path": "/usUnaYV6hQnlVAXP6r4HwrlLFPG.png",
//      "name": "Metro-Goldwyn-Mayer",
//      "origin_country": "US"
//    }
//  ],
//  "production_countries": [
//    {
//      "iso_3166_1": "US",
//      "name": "United States of America"
//    }
//  ],
//  "release_date": "1959-08-06",
//  "revenue": 13275000,
//  "runtime": 136,
//  "spoken_languages": [
//    {
//      "english_name": "English",
//      "iso_639_1": "en",
//      "name": "English"
//    }
//  ],
//  "status": "Released",
//  "tagline": "It's a deadly game of \"tag\" and Cary Grant is \"it\"!",
//  "title": "North by Northwest",
//  "video": false,
//  "vote_average": 7.982,
//  "vote_count": 4302
//}