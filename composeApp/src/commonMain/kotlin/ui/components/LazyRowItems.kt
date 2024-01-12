package ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seiko.imageloader.model.ImageAction
import com.seiko.imageloader.rememberImageSuccessPainter
import com.seiko.imageloader.ui.AutoSizeBox
import domain.model.MediaItem


@Composable
fun MediaItemView(mediaItem: MediaItem, onItemClick: (MediaItem) -> Unit) {
    AutoSizeBox("https://image.tmdb.org/t/p/w500//${mediaItem.posterPath}") { action ->
        when (action) {
            is ImageAction.Success -> {
                Image(
                    rememberImageSuccessPainter(action),
                    contentDescription = "image",
                    modifier = Modifier
                        .width(128.dp)
                        .height(188.dp)
                        .clickable { onItemClick(mediaItem) }
                        .clip(RoundedCornerShape(8.dp))
                )
            }

            is ImageAction.Loading -> {
                Box(contentAlignment = Alignment.Center) {
                    Box(
                        modifier = Modifier
                            .width(128.dp)
                            .background(brush = ShimmerBrush())
                            .height(188.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
            }

            is ImageAction.Failure -> {}
        }
    }
}


@Composable
fun MediaSection(mediaItems: List<MediaItem>, onItemClick: (MediaItem) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        contentAlignment = Alignment.Center
    ) {
        if (mediaItems.isEmpty()) {
            Text(
                "No se encontraron resultados",
                textAlign = TextAlign.Center,
                color = Color.White
            )
        } else {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(mediaItems) { mediaItem ->
                    MediaItemView(mediaItem = mediaItem, onItemClick)
                }
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        modifier = Modifier.padding(start = 15.dp),
        text = title,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Start
        )
    )
}