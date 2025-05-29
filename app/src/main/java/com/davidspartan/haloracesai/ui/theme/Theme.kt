package com.davidspartan.haloracesai.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val AppColorScheme = lightColorScheme(
    primary = GoldenAmber,
    secondary = MidnightBlue,
    tertiary = Pink40,
    background = Black13
)

@Composable
fun HaloracesAITheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = Typography,
        content = content
    )
}