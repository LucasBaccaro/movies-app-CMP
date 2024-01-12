package ui.navigation

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import ui.screens.films.DetailScreen
import ui.screens.films.HomeScreen
import ui.screens.films.OnboardingScreen

@Composable
fun Navigation() {
    val navigator = rememberNavigator()
    NavHost(
        navigator = navigator,
        navTransition = NavTransition(),
        initialRoute = "/onboarding",
    ) {
        scene(
            route = "/onboarding",
            navTransition = NavTransition(),
        ) {
            OnboardingScreen(navigator)
        }
        scene("/home") {
            HomeScreen(navigator)
        }
        scene("/detail") { backStackEntry ->
            DetailScreen(backStackEntry, navigator)
        }
    }
}

