package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun GenreTabsLayout(genreNames: List<String>) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column {
            CategoriesTitle()
            Spacer(Modifier.padding(bottom = 15.dp))
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp)
            ) {
                genreNames.forEachIndexed { index, genreName ->
                    GenreTabItem(genreName = genreName)
                    if (index < genreNames.size - 1) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        }
    }
}


@Composable
fun GenreTabItem(genreName: String) {
    Text(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(Color(0xFFFF1F8A))
            .padding(vertical = 8.dp, horizontal = 12.dp),
        text = genreName,
        color = Color.White,
        textAlign = TextAlign.Center
    )
}