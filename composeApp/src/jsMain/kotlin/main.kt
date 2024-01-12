import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow

import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() = run {
    onWasmReady {
        CanvasBasedWindow("BaccaroLucas") {
            App()
        }
    }
}