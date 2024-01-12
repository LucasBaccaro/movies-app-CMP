package ui.screens.films

import ui.components.Colors
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import getPlatformName
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.BackStackEntry
import moe.tlaster.precompose.navigation.Navigator
import ui.components.BottomBar
import ui.components.CardStats
import ui.components.DetailImage
import ui.components.DetailTopBar
import ui.components.GenreTabsLayout
import ui.components.OverageTitle
import ui.components.OverviewContent
import ui.components.Title
import ui.viewmodels.DetailViewModel
import ui.viewmodels.GenreViewModel

@Composable
fun DetailScreen(backStackEntry: BackStackEntry, navigator: Navigator) {
    val detailViewModel = DetailViewModel(backStackEntry)
    val genreViewModel = koinViewModel(GenreViewModel::class)
    val genreNames = genreViewModel.getGenreNamesByIds(detailViewModel.genresList)

    val platformPaddingTop = if (getPlatformName() == "iOS") 25.dp else 0.dp
    val platformPaddingBottom = if (getPlatformName() == "iOS") 45.dp else 15.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.example)
    ) {
        Scaffold(
            topBar = { },
            bottomBar = { BottomBar("Ver trailer", onClick = {}, platformPaddingBottom) },
            backgroundColor = Color.Transparent
        ) { innerPadding ->
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(innerPadding).verticalScroll(rememberScrollState())
            ) {
                DetailImage(detailViewModel.backdropPath)
                Spacer(Modifier.padding(bottom = 15.dp))
                Title(detailViewModel.title)
                Spacer(Modifier.padding(bottom = 15.dp))
                OverviewContent(detailViewModel.overview)
                Spacer(Modifier.padding(bottom = 15.dp))
                GenreTabsLayout(genreNames)
                Spacer(Modifier.padding(bottom = 15.dp))
                OverageTitle()
                Spacer(Modifier.padding(bottom = 15.dp))
                CardStats(detailViewModel.vote_average)
                Spacer(Modifier.padding(bottom = 15.dp))
            }
            DetailTopBar(navigator, platformPaddingTop)
        }
    }
}






