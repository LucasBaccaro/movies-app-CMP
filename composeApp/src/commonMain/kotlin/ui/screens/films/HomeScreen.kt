package ui.screens.films

import ui.components.Colors
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.model.MediaItem
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.Navigator
import ui.components.CustomTab
import ui.components.HomeTopBar
import ui.components.MediaSection
import ui.components.SearchBar
import ui.components.SectionTitle
import ui.components.SkeletonLazyRow
import ui.viewmodels.MediaType
import ui.viewmodels.MediaViewModel
import ui.viewmodels.SearchViewModel

@Composable
fun HomeScreen(navigator: Navigator) {
    val mediaViewModel = koinViewModel(MediaViewModel::class)
    val searchViewModel = koinViewModel(SearchViewModel::class)

    val state = mediaViewModel.mediaState.collectAsStateWithLifecycle().value
    val searchText by searchViewModel.searchText.collectAsState()
    val filteredTvShowsTrending by searchViewModel.filteredTvShowsTrending.collectAsState()
    val filteredMoviesTrending by searchViewModel.filteredMoviesTrending.collectAsState()
    val filteredTvShowsPopular by searchViewModel.filteredTvShowsPopular.collectAsState()
    val filteredMoviesPopular by searchViewModel.filteredMoviesPopular.collectAsState()

    val tabTitles = listOf("SERIES", "PELICULAS")
    var selectedTabIndex by remember { mutableStateOf(0) }
    val selectedMediaType by mediaViewModel.selectedMediaType.collectAsState()

    val trendingMedia =
        if (selectedMediaType == MediaType.TV_SHOWS) filteredTvShowsTrending else filteredMoviesTrending
    val popularMedia =
        if (selectedMediaType == MediaType.TV_SHOWS) filteredTvShowsPopular else filteredMoviesPopular

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.gradientVerticalBackground),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HomeTopBar()
            SearchBar(
                value = searchText,
                onValueChange = searchViewModel::updateSearchText
            )
            CustomTab(
                selectedItemIndex = selectedTabIndex,
                items = tabTitles,
                onClick = { index ->
                    selectedTabIndex = index
                    mediaViewModel.toggleMediaType()
                }
            )
            when {
                state.isLoading ->
                    Column() {
                    SectionTitle(title = "Tendencias")
                    SkeletonLazyRow()

                    Spacer(modifier = Modifier.height(16.dp))

                    SectionTitle(title = "Más populares")
                    SkeletonLazyRow()
                }
                state.isFailure -> Text(
                    "Error",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                else -> {

                    HomescreenSuccess(
                        trendingMedia = trendingMedia,
                        popularMedia = popularMedia, onItemClick = {
                            val genresString = it.genreIds.joinToString(separator = ",")
                            navigator.navigate("/detail?overview=${it.overview}&backdropPath=${it.backdropPath}&title=${it.title}&vote_average=${it.voteAverage}&genres=$genresString")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun HomescreenSuccess(
    trendingMedia: List<MediaItem>,
    popularMedia: List<MediaItem>,
    onItemClick: (MediaItem) -> Unit
) {
    Column(horizontalAlignment = Alignment.Start) {
        SectionTitle(title = "Tendencias")
        MediaSection(mediaItems = trendingMedia, onItemClick)

        Spacer(modifier = Modifier.height(16.dp)) // Espacio entre secciones

        SectionTitle(title = "Más populares")
        MediaSection(mediaItems = popularMedia, onItemClick)
    }
}