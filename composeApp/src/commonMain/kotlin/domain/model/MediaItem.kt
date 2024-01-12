package domain.model

data class MediaItem(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val backdropPath: String?,
    val posterPath: String,
    val genreIds: List<Int>,
    val originalLanguage: String,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int,
    val mediaType: String,
    val firstAirDate: String?,
    val releaseDate: String?,
    val adult: Boolean?
)