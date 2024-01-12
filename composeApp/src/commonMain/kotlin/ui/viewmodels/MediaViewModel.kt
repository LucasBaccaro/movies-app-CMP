package ui.viewmodels

import core.data.Result
import domain.usecases.GetMoviePopularUseCase
import domain.usecases.GetMovieTrendingUseCase
import domain.usecases.GetTvTrendingUseCase
import domain.model.MediaItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class MediaViewModel(
    private val tvTrendingUseCase: GetTvTrendingUseCase,
    private val movieTrendingUseCase: GetMovieTrendingUseCase,
    private val moviePopularUseCase: GetMoviePopularUseCase
) : ViewModel() {

    private val _mediaState = MutableStateFlow(MediaUiState(isLoading = true))
    val mediaState: StateFlow<MediaUiState> = _mediaState.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _mediaState.update { it.copy(isLoading = false, isFailure = true) }
    }

    private val _selectedMediaType = MutableStateFlow(MediaType.TV_SHOWS)
    val selectedMediaType: StateFlow<MediaType> = _selectedMediaType.asStateFlow()

    init {
        loadMediaData()
    }

    fun toggleMediaType() {
        _selectedMediaType.value =
            if (_selectedMediaType.value == MediaType.TV_SHOWS) MediaType.MOVIES else MediaType.TV_SHOWS
        loadMediaData()
    }

    private fun loadMediaData() {
        if (_selectedMediaType.value == MediaType.TV_SHOWS) {
            fetchTvShowsTrending()
            fethTvShowsPopular()
        }
        if (_selectedMediaType.value == MediaType.MOVIES) {
            fetchMoviesTrending()
            fethMoviesPopular()
        }
    }

    fun fetchTvShowsTrending() {
        viewModelScope.launch(exceptionHandler) {
            _mediaState.update { it.copy(isLoading = true) }
            when (val result = tvTrendingUseCase()) {

                is Result.Success -> _mediaState.update {
                    it.copy(
                        isLoading = false,
                        tvShowsTrending = result.data
                    )
                }

                is Result.Error -> _mediaState.update {
                    it.copy(
                        isLoading = false,
                        isFailure = true
                    )
                }
            }
        }
    }

    fun fetchMoviesTrending() {
        viewModelScope.launch(exceptionHandler) {
            _mediaState.update { it.copy(isLoading = true) }
            when (val result = movieTrendingUseCase()) {
                is Result.Success -> _mediaState.update {
                    it.copy(
                        isLoading = false,
                        moviesTrending = result.data
                    )
                }

                is Result.Error -> _mediaState.update {
                    it.copy(
                        isLoading = false,
                        isFailure = true
                    )
                }
            }
        }
    }

    fun fethMoviesPopular() {
        viewModelScope.launch(exceptionHandler) {
            _mediaState.update { it.copy(isLoading = true) }
            when (val result = moviePopularUseCase()) {
                is Result.Success -> _mediaState.update {
                    it.copy(
                        isLoading = false,
                        moviesPopular = result.data
                    )
                }

                is Result.Error -> _mediaState.update {
                    it.copy(
                        isLoading = false,
                        isFailure = true
                    )
                }
            }
        }
    }

    fun fethTvShowsPopular() {
        viewModelScope.launch(exceptionHandler) {
            _mediaState.update { it.copy(isLoading = true) }
            when (val result = moviePopularUseCase()) {
                is Result.Success -> _mediaState.update {
                    it.copy(
                        isLoading = false,
                        tvShowsPopular = result.data
                    )
                }

                is Result.Error -> _mediaState.update {
                    it.copy(
                        isLoading = false,
                        isFailure = true
                    )
                }
            }
        }
    }
}

enum class MediaType {
    MOVIES, TV_SHOWS
}

data class MediaUiState(
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
    val tvShowsTrending: List<MediaItem> = emptyList(),
    val moviesTrending: List<MediaItem> = emptyList(),
    val moviesPopular: List<MediaItem> = emptyList(),
    val tvShowsPopular: List<MediaItem> = emptyList(),
)