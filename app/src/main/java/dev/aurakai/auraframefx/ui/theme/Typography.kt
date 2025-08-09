package dev.aurakai.auraframefx.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.aurakai.auraframefx.R

// Custom Font Families
val brolinkDemo = FontFamily(
    Font(R.font.brolink_demo, FontWeight.Normal)
)

val ethnocentricRg = FontFamily(
    Font(R.font.ethnocentric_rg, FontWeight.Normal)
)

val AppTypography = Typography(
    // HEADERS - All using Brolink Demo
    displayLarge = TextStyle(
        fontFamily = brolinkDemo,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp
    ),
    displayMedium = TextStyle(
        fontFamily = brolinkDemo,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp
    ),
    displaySmall = TextStyle(
        fontFamily = brolinkDemo,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = brolinkDemo,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = brolinkDemo,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = brolinkDemo,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),

    // CONTENT TEXT - All using Ethnocentric
    titleLarge = TextStyle(
        fontFamily = ethnocentricRg,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp
    ),
    titleMedium = TextStyle(
        fontFamily = ethnocentricRg,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    titleSmall = TextStyle(
        fontFamily = ethnocentricRg,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = ethnocentricRg,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = ethnocentricRg,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = ethnocentricRg,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    labelLarge = TextStyle(
        fontFamily = ethnocentricRg,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = ethnocentricRg,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = ethnocentricRg,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp
    )
)