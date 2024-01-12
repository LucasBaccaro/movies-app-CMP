package ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Title(title: String) {
    Box(
        modifier = Modifier.padding(horizontal = 15.dp)
    ) {
        Column {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFFFFFF)
                )
            )
        }
    }
}

@Composable
fun CategoriesTitle() {
    Box(
    ) {
        Column {
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = "Categoria(s)",
                style = TextStyle(
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFFFFFF),
                )
            )
        }
    }
}

@Composable
fun OverageTitle() {
    Box(
        Modifier.fillMaxWidth()
    ) {
        Column {
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = "Puntuación",
                style = TextStyle(
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFFFFFF),
                )
            )
        }
    }
}


@Composable
fun OverviewContent(overview: String) {
    Box(
        modifier = Modifier.padding(horizontal = 15.dp)
    ) {
        Column {
            Text(overview, color = Color.White)
        }
    }
}