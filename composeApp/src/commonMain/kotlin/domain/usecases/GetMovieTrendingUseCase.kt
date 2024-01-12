package domain.usecases

import core.data.Result
import core.data.mapSuccess
import core.data.models.ContentTypeTrending
import core.data.models.MovieDTO
import core.data.models.TrendingResponse
import core.data.movies.TmdbRepository
import core.data.toSuccess
import domain.model.MediaItem

class GetMovieTrendingUseCase(private val tmdbRepository: TmdbRepository) {
    suspend operator fun invoke(): Result<List<MediaItem>> {
        return tmdbRepository.getTrending(ContentTypeTrending.MovieTrending)
            .mapSuccess { response ->
                when (response.data) {
                    is TrendingResponse.Movies -> response.data.response.results.map { it.asMediaItem() }
                        .toSuccess()

                    else -> Result.Error("Tipo de respuesta no esperado")
                }
            }
    }
}

fun MovieDTO.asMediaItem(): MediaItem {
    return MediaItem(
        id = this.id,
        title = this.title,
        originalTitle = this.original_title,
        overview = this.overview,
        backdropPath = this.backdrop_path,
        posterPath = this.poster_path!!,
        genreIds = this.genre_ids,
        originalLanguage = this.original_language,
        popularity = this.popularity,
        voteAverage = this.vote_average,
        voteCount = this.vote_count,
        mediaType = this.media_type ?: "",
        firstAirDate = null,
        releaseDate = this.release_date,
        adult = this.adult
    )
}