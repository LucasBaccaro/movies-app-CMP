package ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar() {
    Box(modifier = Modifier.padding(top = 10.dp, bottom = 30.dp)) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
            title = {
                Text(
                    text = "Bienvenido Lucas Baccaro!",
                    style = TextStyle(
                        fontSize = 26.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                    )
                )
            },
            navigationIcon = {},
            actions = {
                Image(
                    painter = painterResource("profile.jpeg"),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(BorderStroke(1.dp, Color.White), CircleShape)
                        .padding(2.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Fit
                )
            },
        )
    }
}
