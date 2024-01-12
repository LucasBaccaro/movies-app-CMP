package ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

class Colors {
    companion object {
        val gradientVerticalBackground: Brush
            get() = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF8000FF),
                    Color(0xFF000000)
                )
            )
        val gradientVerticalButton: Brush
            get() = Brush.horizontalGradient(
                colors = listOf(
                    Color(0xFF8000FF), // Primer color en formato hexadecimal
                    Color(0xFF540BA1), // Segundo color en formato hexadecimal
                )
            )

        val example: Brush
            get() = Brush.verticalGradient(
                colors = listOf(
                    Color.Transparent, // Comienza con transparente
                    Color(0xFF000000), // Se mezcla con negro
                    Color(0xFF8000FF)  // Termina con violeta
                )
            )
    }
}

@Composable
fun ShimmerBrush(): Brush {
    val shimmerColors = listOf(
        Color(0xFF21222E).copy(alpha = 0.9f),
        Color.LightGray.copy(alpha = 0.3f),
        Color(0xFF21222E).copy(alpha = 0.9f)
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = LinearEasing),
            RepeatMode.Restart
        )
    )

    return Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(10f, 10f),
        end = Offset(translateAnim.value, translateAnim.value)
    )
}

