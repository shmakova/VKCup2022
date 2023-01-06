package ru.shmakova.vkcup.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color.White,
    onPrimary = Color.Black,
    secondary = Color(0x2BFFFFFF),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFF5317),
    onSecondaryContainer = Color.White,
    tertiary = Color(0x1FFFFFFF),
    onTertiary = Color.White,
    background = Color.Black,
    onBackground = Color(0x7AFFFFFF)
)

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.background.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
