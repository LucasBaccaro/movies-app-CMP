package domain.usecases

import core.data.Result
import core.data.genres.MovieGenreRepository
import core.data.mapSuccess
import core.data.models.Genre
import core.data.toSuccess

class GetMovieGenresUseCase(
    private val movieGenreRepository: MovieGenreRepository,
) {
    suspend operator fun invoke(): Result<List<GenreModel>> {
        return movieGenreRepository.getMovieGenres().mapSuccess { it ->
            it.data.map { it.asGenreModel() }.toSuccess()
        }
    }
}

fun Genre.asGenreModel(): GenreModel {
    return GenreModel(
        id = this.id,
        name = this.name
    )
}

data class GenreModel(val id: Int, val name: String)