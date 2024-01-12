import androidx.compose.runtime.Composable
import di.appModule
import moe.tlaster.precompose.PreComposeApp
import org.koin.compose.KoinApplication
import ui.navigation.Navigation

@Composable
fun App() {
    KoinApplication(moduleList = { appModule() }) {
        PreComposeApp {
            Navigation()
        }
    }
}


