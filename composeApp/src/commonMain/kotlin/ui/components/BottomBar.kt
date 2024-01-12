package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomBar(title: String, onClick: () -> Unit, bottomPading: Dp) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = bottomPading)
    ) {
        Box(
            modifier = Modifier
                .width(314.dp)
                .height(43.dp)
                .align(Alignment.BottomCenter)
                .background(Colors.gradientVerticalButton, shape = CircleShape)
        ) {
            Button(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp)
            ) {
                Text(
                    title,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600)
                )
            }
        }
    }
}