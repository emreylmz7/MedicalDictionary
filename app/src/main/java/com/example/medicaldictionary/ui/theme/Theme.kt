package com.example.medicaldictionary.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColorScheme(
    primary = PrimaryColor,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryVariant,
    secondary = SecondaryColor,
    onSecondary = OnSecondary,
    background = BackgroundColor,
    onBackground = OnBackground,
    surface = SurfaceColor,
    onSurface = OnSurface,
    error = ErrorColor,
    onError = OnError
)

private val DarkThemeColors = darkColorScheme(
    primary = PrimaryColor,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryVariant,
    secondary = SecondaryColor,
    onSecondary = OnSecondary,
    background = Color.Black,
    onBackground = OnBackground,
    surface = Color.DarkGray,
    onSurface = OnSurface,
    error = ErrorColor,
    onError = OnError
)

@Composable
fun MedicalDictionaryTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkThemeColors else LightThemeColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
