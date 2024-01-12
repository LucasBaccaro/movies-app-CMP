package ui.viewmodels

import domain.model.MediaItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class SearchViewModel(private val mediaViewModel: MediaViewModel) : ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    val filteredTvShowsTrending: StateFlow<List<MediaItem>> = mediaViewModel.mediaState
        .combine(searchText) { state, searchText ->
            if (searchText.isBlank()) state.tvShowsTrending
            else state.tvShowsTrending.filter { it.title.contains(searchText, ignoreCase = true) }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val filteredMoviesTrending: StateFlow<List<MediaItem>> = mediaViewModel.mediaState
        .combine(searchText) { state, searchText ->
            if (searchText.isBlank()) state.moviesTrending
            else state.moviesTrending.filter { it.title.contains(searchText, ignoreCase = true) }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val filteredTvShowsPopular: StateFlow<List<MediaItem>> = mediaViewModel.mediaState
        .combine(searchText) { state, searchText ->
            if (searchText.isBlank()) state.tvShowsPopular
            else state.tvShowsPopular.filter { it.title.contains(searchText, ignoreCase = true) }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val filteredMoviesPopular: StateFlow<List<MediaItem>> = mediaViewModel.mediaState
        .combine(searchText) { state, searchText ->
            if (searchText.isBlank()) state.moviesPopular
            else state.moviesPopular.filter { it.title.contains(searchText, ignoreCase = true) }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun updateSearchText(newText: String) {
        _searchText.value = newText
    }
}