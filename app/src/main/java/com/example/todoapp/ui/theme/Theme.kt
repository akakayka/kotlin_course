package com.example.todoapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFDC143C),       // Яркий алый
    onPrimary = Color.White,           // Белый текст на алом
    primaryContainer = Color(0xFFFFCDD2), // Светло-розовый контейнер
    onPrimaryContainer = Color(0xFF8B0000), // Темно-красный текст в контейнере

    secondary = Color(0xFF8B0000),     // Темно-красный
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFFE0E0),
    onSecondaryContainer = Color(0xFF5A0000),

    tertiary = Color(0xFFA52A2A),      // Коричнево-красный
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFFDADA),
    onTertiaryContainer = Color(0xFF6B0000),

    background = Color(0xFFFFFBFF),    // Чисто белый фон
    onBackground = Color(0xFF1C1B1F),  // Темно-серый текст

    surface = Color(0xFFFFFBFF),       // Белая поверхность
    onSurface = Color(0xFF1C1B1F),     // Темно-серый текст на поверхности

    surfaceVariant = Color(0xFFF5F5F5), // Светло-серый вариант поверхности
    onSurfaceVariant = Color(0xFF49454F), // Серый текст

    outline = Color(0xFF79747E),       // Серый контур
    outlineVariant = Color(0xFFC4C7C5) // Светло-серый вариант контура
)


private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFF6B6B),       // Светло-алый
    onPrimary = Color.Black,           // Черный текст на алом
    primaryContainer = Color(0xFF8B0000), // Темно-красный контейнер
    onPrimaryContainer = Color(0xFFFFCDD2), // Светло-розовый текст в контейнере

    secondary = Color(0xFFFF8A80),     // Светло-красный
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF5A0000),
    onSecondaryContainer = Color(0xFFFFE0E0),

    tertiary = Color(0xFFFFAB91),      // Светло-оранжево-красный
    onTertiary = Color.Black,
    tertiaryContainer = Color(0xFF6B0000),
    onTertiaryContainer = Color(0xFFFFDADA),

    background = Color(0xFF121212),    // Темный фон
    onBackground = Color(0xFFE6E1E5),  // Светлый текст

    surface = Color(0xFF1E1E1E),       // Темная поверхность
    onSurface = Color(0xFFE6E1E5),     // Светлый текст на поверхности

    surfaceVariant = Color(0xFF2D2D2D), // Темно-серый вариант поверхности
    onSurfaceVariant = Color(0xFFCAC4D0), // Светло-серый текст

    outline = Color(0xFF938F99),       // Серый контур
    outlineVariant = Color(0xFF444746) // Темно-серый вариант контура
)

@Composable
fun ToDoAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}