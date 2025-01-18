package com.smallrents.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = BlueConstruction,
    onPrimary = WhiteConstruction,
    primaryContainer = WhiteConstruction,
    onPrimaryContainer = DarkWhiteConstruction,
   // inversePrimary = Red,

    secondary = OrangeConstruction,
    onSecondary = WhiteConstruction,
   // secondaryContainer = Red,
   // onSecondaryContainer = Red,

    tertiary = YellowConstruction,
    onTertiary = WhiteConstruction,
  // tertiaryContainer = Red,
   // onTertiaryContainer = Red,

    background = Color(0xFFfcf8f9),
    //onBackground = Red,

    surface = WhiteConstruction,
/*    onSurface = Red,
    surfaceVariant = Red,
    onSurfaceVariant = Red,
    surfaceTint = Red,
   inverseSurface =Red,
   inverseOnSurface = Red,*/
//
//    error = generateRandomColor(),
//    onError = generateRandomColor(),
//    errorContainer = generateRandomColor(),
//    onErrorContainer = generateRandomColor(),
//
//    outline = generateRandomColor(),
//    outlineVariant = generateRandomColor(),
//
/*    scrim = Red,
//
    surfaceBright =Red,
    surfaceContainer = Red,
    surfaceContainerHigh = Red,*/
    surfaceContainerHighest = Color(0xFFe5e2e2),
   // surfaceContainerLow = Red,
   //surfaceContainerLowest = Red,
  //surfaceDim = Red,
)

@Composable
fun SmallRentsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
      /*dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }*/
      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

    MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      content = content
    )
}