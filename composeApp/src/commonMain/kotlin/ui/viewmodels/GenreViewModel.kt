package ui.viewmodels

import core.data.Result
import domain.usecases.GenreModel
import domain.usecases.GetMovieGenresUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class GenreViewModel(
    private val genresUseCase: GetMovieGenresUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(GenreUiState(isLoading = true))
    val state = _state.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _state.update { it.copy(isLoading = false, isFailure = true) }
    }

    init {
        fetch()
    }

    fun fetch() {
        viewModelScope.launch(exceptionHandler) {
            _state.update { it.copy(isLoading = true) }
            when (val result = genresUseCase()) {

                is Result.Success -> {
                    _state.update { GenreUiState(isLoading = false, data = result.data) }
                }

                is Result.Error -> {
                    _state.update { GenreUiState(isLoading = false, isFailure = true) }
                }
            }
        }
    }

    fun getGenreNamesByIds(ids: List<Int>): List<String> {
        return state.value.data.filter { it.id in ids }.map { it.name }
    }

}

data class GenreUiState(
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
    val data: List<GenreModel> = emptyList(),
)
