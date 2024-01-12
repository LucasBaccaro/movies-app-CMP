package core.data.genres

import core.data.Result
import core.data.models.ErrorResponse
import core.data.models.Genre
import core.data.models.GenreResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class MovieGenreRepository(private val client: HttpClient) {
    suspend fun getMovieGenres(): Result<List<Genre>> {
        return try {
            val response: HttpResponse = client.get("/3/genre/movie/list?language=en")
            val responseBody: String = response.bodyAsText()

            if (response.status != HttpStatusCode.OK) {
                val errorResponse: ErrorResponse = Json.decodeFromString(responseBody)
                return Result.Error("Error de la API: ${errorResponse.status_message}")
            }

            val genreResponse: GenreResponse = Json.decodeFromString(responseBody)
            Result.Success(genreResponse.genres)
        } catch (e: Exception) {
            Result.Error("Error al obtener los datos: ${e.message}")
        }
    }
}
