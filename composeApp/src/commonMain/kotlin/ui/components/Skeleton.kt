package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun SkeletonLazyRow() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp),) {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(25) {
                SkeletonBox()
            }
        }
    }

}

@Composable
fun SkeletonBox() {
    Box(
        modifier = Modifier
            .width(128.dp)
            .height(188.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(brush = ShimmerBrush()),
        contentAlignment = Alignment.Center
    ) {}
}
