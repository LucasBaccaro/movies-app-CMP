package ui.viewmodels

import moe.tlaster.precompose.navigation.BackStackEntry
import moe.tlaster.precompose.navigation.query

class DetailViewModel(backStackEntry: BackStackEntry) {
    val overview: String = backStackEntry.query<String>("overview") ?: ""
    val backdropPath: String = backStackEntry.query<String>("backdropPath") ?: ""
    val title: String = backStackEntry.query<String>("title") ?: ""
    val vote_average: String = backStackEntry.query<String>("vote_average") ?: ""
    val genresString = backStackEntry.query<String>("genres") ?: ""
    val genresList = genresString?.split(",")?.mapNotNull { it.toIntOrNull() } ?: listOf()
}