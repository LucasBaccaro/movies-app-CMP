package domain.usecases

import core.data.Result
import core.data.mapSuccess
import core.data.models.ContentTypePopular
import core.data.models.PopularResponse
import core.data.movies.TmdbRepository
import core.data.toSuccess
import domain.model.MediaItem

class GetTvShowPopularUseCase(private val tmdbRepository: TmdbRepository) {
    suspend operator fun invoke(): Result<List<MediaItem>> {
        return tmdbRepository.getPopulars(ContentTypePopular.TvPopular).mapSuccess { response ->
            when (response.data) {
                is PopularResponse.TvShows -> response.data.response.results.map { it.asMediaItem() }
                    .toSuccess()

                else -> Result.Error("Tipo de respuesta no esperado")
            }
        }
    }
}