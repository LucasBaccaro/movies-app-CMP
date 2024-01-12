package core.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(val status_code: Int, val status_message: String)

@Serializable
data class GenreResponse(val genres: List<Genre>)

@Serializable
data class Genre(val id: Int, val name: String)


sealed class TrendingResponse {
    data class TvShows(val response: TvShowResponse) : TrendingResponse()
    data class Movies(val response: MovieResponse) : TrendingResponse()
}

sealed class PopularResponse {
    data class TvShows(val response: TvShowResponse) : TrendingResponse()
    data class Movies(val response: MovieResponse) : TrendingResponse()
}


@Serializable
data class TvShowResponse(
    val page: Int,
    val results: List<TvShowDTO>,
    val total_pages: Int,
    val total_results: Int
)

@Serializable
data class MovieResponse(
    val page: Int,
    val results: List<MovieDTO>,
    val total_pages: Int,
    val total_results: Int
)

@Serializable
data class TvShowDTO(
    val adult: Boolean?,
    val id: Int,
    val name: String,
    val original_name: String,
    val overview: String,
    val first_air_date: String,
    val backdrop_path: String?,
    val poster_path: String?,
    val genre_ids: List<Int>,
    val original_language: String,
    val popularity: Double,
    val vote_average: Double,
    val vote_count: Int,
    val media_type: String? = null,
    val origin_country: List<String>
)

@Serializable
data class MovieDTO(
    val id: Int,
    val title: String,
    val original_title: String,
    val overview: String,
    val release_date: String,
    val backdrop_path: String?,
    val poster_path: String?,
    val genre_ids: List<Int>,
    val original_language: String,
    val popularity: Double,
    val vote_average: Double,
    val vote_count: Int,
    val adult: Boolean,
    val media_type: String? = null,
    val video: Boolean?,
)

enum class ContentTypeTrending {
    MovieTrending, TvTrending
}

enum class ContentTypePopular {
    MoviePopular, TvPopular
}