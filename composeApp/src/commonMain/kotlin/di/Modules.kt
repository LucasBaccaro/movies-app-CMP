package di

import core.data.movies.TmdbRepository
import core.data.genres.MovieGenreRepository
import core.network.ktorHttpClient
import domain.usecases.GetMovieGenresUseCase
import domain.usecases.GetMoviePopularUseCase
import domain.usecases.GetMovieTrendingUseCase
import domain.usecases.GetTvTrendingUseCase
import org.koin.dsl.module
import ui.viewmodels.GenreViewModel
import ui.viewmodels.MediaViewModel
import ui.viewmodels.SearchViewModel

val sharedModules = module {

    single { ktorHttpClient() }
    single { MovieGenreRepository(client = get()) }
    single { GetMovieGenresUseCase(movieGenreRepository = get()) }
    single { GenreViewModel(genresUseCase = get()) }

    single { TmdbRepository(client = get()) }
    single { GetTvTrendingUseCase(tmdbRepository = get()) }
    single { GetMovieTrendingUseCase(tmdbRepository = get()) }
    single { GetMoviePopularUseCase(tmdbRepository = get()) }
    single {
        MediaViewModel(
            tvTrendingUseCase = get(),
            movieTrendingUseCase = get(),
            moviePopularUseCase = get()
        )
    }
    single {
        SearchViewModel(mediaViewModel = get())
    }
}

fun appModule() = listOf(sharedModules)