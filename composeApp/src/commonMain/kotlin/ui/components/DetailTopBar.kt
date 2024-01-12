package ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun DetailTopBar(navigator: Navigator, topPadding: Dp) {
    Box(
        modifier = Modifier.padding(top = topPadding)
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BackIcon(navigator)
            Spacer(modifier = Modifier.weight(1f))
            FavoriteIcon()
        }
    }
}

@Composable
fun BackIcon(navigator: Navigator) {
    Icon(
        imageVector = Icons.Filled.ArrowBack,
        tint = Color.White,
        contentDescription = "Back",
        modifier = Modifier
            .size(35.dp)
            .clickable { navigator.goBack() }
    )
}

@Composable
fun FavoriteIcon() {
    Icon(
        imageVector = Icons.Filled.FavoriteBorder,
        tint = Color.White,
        contentDescription = "Favorite",
        modifier = Modifier
            .size(35.dp)
    )
}
