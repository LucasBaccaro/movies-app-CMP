package ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.model.ImageAction
import com.seiko.imageloader.rememberImageSuccessPainter
import com.seiko.imageloader.ui.AutoSizeBox

@Composable
fun ImageComponent(action: ImageAction.Success) {
    Image(
        rememberImageSuccessPainter(action),
        contentDescription = "image",
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 600.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun DetailImage(backdropPath: String) {
    AutoSizeBox("https://image.tmdb.org/t/p/w500//${backdropPath}") { action ->
        when (action) {
            is ImageAction.Success -> ImageComponent(action)
            is ImageAction.Loading -> {}
            is ImageAction.Failure -> {}
        }
    }
}