package my.tech.calculator.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimaryColor,
    onPrimary = DarkOnPrimaryColor,
    secondary = DarkSecondaryColor,
    onSecondary = DarkOnSecondaryColor,
    background = DarkBackground,
    surface = DarkSurface,
    onSurface = DarkOnSurfaceColor
)

private val LightColorScheme = lightColorScheme(
    primary = LightPrimaryColor,
    onPrimary = LightOnPrimaryColor,
    secondary = LightSecondaryColor,
    onSecondary = LightOnSecondaryColor,
    background = LightBackground,
    surface = LightSurface,
    onSurface = LightOnSurfaceColor
)

@Composable
fun MyTechCalculatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}