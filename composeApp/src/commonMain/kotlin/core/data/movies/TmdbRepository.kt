package core.data.movies

import core.data.Result
import core.data.models.ContentTypePopular
import core.data.models.ContentTypeTrending
import core.data.models.ErrorResponse
import core.data.models.PopularResponse
import core.data.models.TrendingResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.Json

class TmdbRepository(private val client: HttpClient) {
    suspend fun getTrending(contentType: ContentTypeTrending): Result<TrendingResponse> {
        val url = when (contentType) {
            ContentTypeTrending.MovieTrending -> "/3/trending/movie/day?language=en-US"
            ContentTypeTrending.TvTrending -> "/3/trending/tv/day?language=en-US"
        }

        return try {
            val response: HttpResponse = client.get(url)
            val responseBody: String = response.bodyAsText()

            if (response.status != HttpStatusCode.OK) {
                val errorResponse: ErrorResponse = Json.decodeFromString(responseBody)
                return Result.Error("Error de la API: ${errorResponse.status_message}")
            }

            when (contentType) {
                ContentTypeTrending.MovieTrending -> Result.Success(
                    TrendingResponse.Movies(
                        Json.decodeFromString(
                            responseBody
                        )
                    )
                )

                ContentTypeTrending.TvTrending -> Result.Success(
                    TrendingResponse.TvShows(
                        Json.decodeFromString(
                            responseBody
                        )
                    )
                )
            }
        } catch (e: Exception) {
            Result.Error("Error al obtener los datos: ${e.message}")
        }
    }

    suspend fun getPopulars(contentType: ContentTypePopular): Result<TrendingResponse> {
        val url = when (contentType) {
            ContentTypePopular.MoviePopular -> "3/movie/popular?language=en-US&page=1"
            ContentTypePopular.TvPopular -> "3/tv/popular?language=en-US&page=1"
        }

        return try {
            val response: HttpResponse = client.get(url)
            val responseBody: String = response.bodyAsText()

            if (response.status != HttpStatusCode.OK) {
                val errorResponse: ErrorResponse = Json.decodeFromString(responseBody)
                return Result.Error("Error de la API: ${errorResponse.status_message}")
            }

            when (contentType) {
                ContentTypePopular.MoviePopular -> Result.Success(
                    PopularResponse.Movies(
                        Json.decodeFromString(
                            responseBody
                        )
                    )
                )

                ContentTypePopular.TvPopular -> Result.Success(
                    PopularResponse.TvShows(
                        Json.decodeFromString(
                            responseBody
                        )
                    )
                )
            }
        } catch (e: Exception) {
            Result.Error("Error al obtener los datos: ${e.message}")
        }
    }
}
