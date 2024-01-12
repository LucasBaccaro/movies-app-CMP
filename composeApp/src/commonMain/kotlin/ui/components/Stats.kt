package ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt


@Composable
fun CardStats(vote_average: String) {
    Box(
        modifier = Modifier
            .background(Color.Transparent)
    ) {
        Column {
            Row(horizontalArrangement = Arrangement.Center) {
                CircularPercentageView(vote_average)
                RatingStars(vote_average)
            }
        }
    }
}

@Composable
fun CircularPercentageView(vote_average: String) {
    val voteAverage = vote_average.toDouble()
    val percentage = (voteAverage * 10).roundToInt()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(45.dp)
            .background(Color.Black, shape = CircleShape)
            .clip(CircleShape)
    ) {
        Canvas(modifier = Modifier.size(45.dp)) {
            drawArc(
                color = Color(0xFFFF1F8A),
                startAngle = -90f,
                sweepAngle = 360 * (percentage / 100f),
                useCenter = false,
                style = Stroke(width = 10.dp.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = "$percentage%",
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        )
    }
}


@Composable
fun RatingStars(vote_average: String) {
    val roundedVoteAverage = vote_average.toDouble().roundToInt()
    val fullStars = roundedVoteAverage / 2
    val halfStar = roundedVoteAverage % 2 != 0

    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until fullStars) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Full Star",
                tint = Color.Yellow
            )
        }
        if (halfStar) {
            Icon(
                imageVector = Icons.Filled.StarHalf,
                contentDescription = "Half Star",
                tint = Color.Yellow
            )
        }
        for (i in (if (halfStar) fullStars + 1 else fullStars) until 5) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = "Outlined Star",
                tint = Color.Gray
            )
        }
    }
}
