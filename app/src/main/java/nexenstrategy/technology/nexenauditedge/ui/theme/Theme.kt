package nexenstrategy.technology.nexenauditedge.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF64B5F6), secondary = Color(0xFF4DB6AC), tertiary = NexenWarning,
    background = Color(0xFF0E1720), surface = Color(0xFF17232E), onSurface = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = NexenPrimary, secondary = NexenAccent, tertiary = NexenWarning,
    background = NexenBackground, surface = NexenSurface, onPrimary = Color.White,
    onSecondary = Color.White, onBackground = NexenText, onSurface = NexenText,
    onSurfaceVariant = NexenMuted, outline = NexenBorder, error = Color(0xFFB3261E),
)

@Composable
fun ServiceSkeletonTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
