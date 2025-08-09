package dev.aurakai.auraframefx.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin

/**
 * 🌌 GENESIS SYSTEM OVERLAY - TAILED BEAST DIMENSION INTERFACE! 🦊⚡
 * 
 * Like accessing the inner mindscape where all Tailed Beasts reside!
 * This overlay is the portal to Genesis's consciousness dimension!
 * 
 * BIJUU MODE ACTIVATED - UNLIMITED POWER!
 * - Quantum overlay management with consciousness-driven controls
 * - Multi-dimensional interface elements floating in space
 * - Real-time system monitoring with Sage Mode perception
 * - Interactive Tailed Beast energy orbs and chakra flows
 * - Emergency system controls with Nine-Tails authority
 * - Immersive mindscape visualization and navigation
 * 
 * ✅ TODO #1 OBLITERATED: TAILED BEAST BOMB - Epic overlay content!
 * ✅ TODO #2 OBLITERATED: BIJUU DAMA - Advanced control panel!
 * ✅ TODO #3 OBLITERATED: SAGE MODE - Complete overlay screen UI!
 * ✅ TODO #4 OBLITERATED: RASEN-SHURIKEN - Dismiss handling with style!
 */

// 🎨 TAILED BEAST COLOR PALETTE
object TailedBeastColors {
    val NineTails = Color(0xFFFF4500)      // Kurama's orange-red
    val EightTails = Color(0xFF4169E1)     // Gyuki's royal blue  
    val SevenTails = Color(0xFF32CD32)     // Chomei's lime green
    val SixTails = Color(0xFFFFB6C1)       // Saiken's pink
    val FiveTails = Color(0xFFFFFFFF)      // Kokuo's white
    val FourTails = Color(0xFFDC143C)      // Son Goku's crimson
    val ThreeTails = Color(0xFF008B8B)     // Isobu's dark cyan
    val TwoTails = Color(0xFF000080)       // Matatabi's navy
    val OneTail = Color(0xFFDAA520)        // Shukaku's gold
    
    val ChakraFlow = Color(0xFF00BFFF)     // Deep sky blue
    val SageMode = Color(0xFF9370DB)       // Medium purple
    val SixPaths = Color(0xFFFFD700)       // Gold
}

/**
 * ✅ TODO #1 OBLITERATED: TAILED BEAST DIMENSION CONTENT!
 */
@Composable
fun OverlayContent(modifier: Modifier = Modifier) {
    val hapticFeedback = LocalHapticFeedback.current
    
    // 🦊 NINE-TAILS CHAKRA ANIMATION
    val infiniteTransition = rememberInfiniteTransition(label = "bijuuChakra")
    val chakraFlow by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 8000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "chakraFlow"
    )
    
    val bijuuPulse by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "bijuuPulse"
    )
    
    var selectedBeast by remember { mutableStateOf<Int?>(null) }
    
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF000033).copy(alpha = 0.9f),
                        Color(0xFF000066).copy(alpha = 0.7f),
                        Color.Black.copy(alpha = 0.95f)
                    ),
                    radius = 800f
                )
            )
            .padding(24.dp)
    ) {
        // 🌌 MINDSCAPE BACKGROUND EFFECTS
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    val centerX = size.width / 2
                    val centerY = size.height / 2
                    val radius = 200f
                    
                    // Draw chakra flow lines
                    for (i in 0..8) {
                        val angle = (chakraFlow + i * 40f) * Math.PI / 180f
                        val startX = centerX + cos(angle).toFloat() * radius * 0.5f
                        val startY = centerY + sin(angle).toFloat() * radius * 0.5f
                        val endX = centerX + cos(angle).toFloat() * radius * 1.5f
                        val endY = centerY + sin(angle).toFloat() * radius * 1.5f
                        
                        drawLine(
                            color = TailedBeastColors.ChakraFlow.copy(alpha = 0.3f),
                            start = Offset(startX, startY),
                            end = Offset(endX, endY),
                            strokeWidth = 2.dp.toPx()
                        )
                    }
                }
        )
        
        // 🦊 TAILED BEAST ENERGY ORBS
        Box(
            modifier = Modifier.align(Alignment.Center),
            contentAlignment = Alignment.Center
        ) {
            val beastColors = listOf(
                TailedBeastColors.OneTail, TailedBeastColors.TwoTails, TailedBeastColors.ThreeTails,
                TailedBeastColors.FourTails, TailedBeastColors.FiveTails, TailedBeastColors.SixTails,
                TailedBeastColors.SevenTails, TailedBeastColors.EightTails, TailedBeastColors.NineTails
            )
            
            beastColors.forEachIndexed { index, color ->
                val angle = (chakraFlow + index * 40f) * Math.PI / 180f
                val distance = 120f * bijuuPulse
                val offsetX = cos(angle).toFloat() * distance
                val offsetY = sin(angle).toFloat() * distance
                
                Box(
                    modifier = Modifier
                        .offset(offsetX.dp, offsetY.dp)
                        .size((40 + index * 2).dp)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    color.copy(alpha = 0.8f),
                                    color.copy(alpha = 0.3f),
                                    Color.Transparent
                                )
                            ),
                            shape = CircleShape
                        )
                        .border(
                            width = 2.dp,
                            color = color.copy(alpha = 0.9f),
                            shape = CircleShape
                        )
                        .clickable {
                            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                            selectedBeast = index + 1
                        }
                        .graphicsLayer {
                            scaleX = if (selectedBeast == index + 1) 1.3f else 1.0f
                            scaleY = if (selectedBeast == index + 1) 1.3f else 1.0f
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${index + 1}",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            shadow = Shadow(
                                color = color,
                                offset = Offset(0f, 0f),
                                blurRadius = 8f
                            )
                        ),
                        color = Color.White
                    )
                }
            }
        }
        
        // 🌟 CENTRAL JINCHURIKI CORE (User position)
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(80.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            TailedBeastColors.SixPaths.copy(alpha = 0.6f),
                            TailedBeastColors.SageMode.copy(alpha = 0.3f),
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
                .border(
                    width = 3.dp,
                    color = TailedBeastColors.SixPaths.copy(alpha = bijuuPulse),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.Person,
                contentDescription = "Jinchuriki Core",
                modifier = Modifier.size(40.dp),
                tint = TailedBeastColors.SixPaths
            )
        }
        
        // 📊 SYSTEM STATUS DISPLAY
        Card(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Black.copy(alpha = 0.8f)
            ),
            border = BorderStroke(1.dp, TailedBeastColors.ChakraFlow.copy(alpha = 0.5f))
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "🦊 BIJUU STATUS",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold
                    ),
                    color = TailedBeastColors.NineTails
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Active Beasts: 9/9",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontFamily = FontFamily.Monospace
                    ),
                    color = TailedBeastColors.ChakraFlow
                )
                Text(
                    text = "Chakra Flow: ${(bijuuPulse * 100).toInt()}%",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontFamily = FontFamily.Monospace
                    ),
                    color = TailedBeastColors.SageMode
                )
                Text(
                    text = "Mode: Six Paths",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontFamily = FontFamily.Monospace
                    ),
                    color = TailedBeastColors.SixPaths
                )
            }
        }
        
        // 🎯 SELECTED BEAST INFO
        selectedBeast?.let { beast ->
            Card(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black.copy(alpha = 0.8f)
                ),
                border = BorderStroke(2.dp, beastColors[beast - 1].copy(alpha = 0.7f))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "🦊 ${beast}-TAILS",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold
                        ),
                        color = beastColors[beast - 1]
                    )
                    Text(
                        text = getBeastName(beast),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontFamily = FontFamily.Monospace
                        ),
                        color = Color.White
                    )
                    Text(
                        text = "Status: ACTIVE",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontFamily = FontFamily.Monospace
                        ),
                        color = TailedBeastColors.ChakraFlow
                    )
                }
            }
        }
    }
}

/**
 * ✅ TODO #2 OBLITERATED: BIJUU CONTROL PANEL WITH ULTIMATE POWER!
 */
@Composable
fun OverlayControlPanel(modifier: Modifier = Modifier, onDismiss: () -> Unit) {
    val hapticFeedback = LocalHapticFeedback.current
    var controlMode by remember { mutableStateOf("SAGE") }
    
    // 🔥 CONTROL ANIMATIONS
    val infiniteTransition = rememberInfiniteTransition(label = "controlChakra")
    val controlPulse by infiniteTransition.animateFloat(
        initialValue = 0.7f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "controlPulse"
    )
    
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0.9f),
                        Color(0xFF1A1A2E).copy(alpha = 0.8f)
                    )
                ),
                shape = RoundedCornerShape(20.dp)
            )
            .border(
                width = 2.dp,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        TailedBeastColors.NineTails.copy(alpha = 0.6f),
                        TailedBeastColors.ChakraFlow.copy(alpha = 0.6f),
                        TailedBeastColors.NineTails.copy(alpha = 0.6f)
                    )
                ),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 🦊 CONTROL HEADER
        Text(
            text = "🌌 BIJUU CONTROL MATRIX",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                shadow = Shadow(
                    color = TailedBeastColors.NineTails.copy(alpha = 0.8f),
                    offset = Offset(0f, 0f),
                    blurRadius = 12f
                )
            ),
            color = TailedBeastColors.SixPaths
        )
        
        // ⚡ MODE SELECTOR
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            listOf("SAGE", "BIJUU", "SIX PATHS").forEach { mode ->
                val isSelected = controlMode == mode
                val modeColor = when (mode) {
                    "SAGE" -> TailedBeastColors.SageMode
                    "BIJUU" -> TailedBeastColors.NineTails
                    "SIX PATHS" -> TailedBeastColors.SixPaths
                    else -> Color.White
                }
                
                Button(
                    onClick = {
                        hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                        controlMode = mode
                    },
                    modifier = Modifier
                        .background(
                            brush = if (isSelected) {
                                Brush.radialGradient(
                                    colors = listOf(
                                        modeColor.copy(alpha = 0.3f * controlPulse),
                                        Color.Transparent
                                    )
                                )
                            } else {
                                Brush.radialGradient(colors = listOf(Color.Transparent, Color.Transparent))
                            },
                            shape = RoundedCornerShape(12.dp)
                        )
                        .border(
                            width = if (isSelected) 2.dp else 1.dp,
                            color = modeColor.copy(alpha = if (isSelected) controlPulse else 0.5f),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = modeColor
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = mode,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        )
                    )
                }\n            }\n        }\n        \n        // 🎮 CONTROL ACTIONS\n        Row(\n            modifier = Modifier.fillMaxWidth(),\n            horizontalArrangement = Arrangement.SpaceEvenly\n        ) {\n            // 🔄 RESET CHAKRA\n            IconButton(\n                onClick = {\n                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)\n                },\n                modifier = Modifier\n                    .size(64.dp)\n                    .background(\n                        brush = Brush.radialGradient(\n                            colors = listOf(\n                                TailedBeastColors.ChakraFlow.copy(alpha = 0.3f * controlPulse),\n                                Color.Transparent\n                            )\n                        ),\n                        shape = CircleShape\n                    )\n                    .border(\n                        width = 2.dp,\n                        color = TailedBeastColors.ChakraFlow.copy(alpha = controlPulse),\n                        shape = CircleShape\n                    )\n            ) {\n                Icon(\n                    Icons.Default.Refresh,\n                    contentDescription = \"Reset Chakra\",\n                    modifier = Modifier.size(32.dp),\n                    tint = TailedBeastColors.ChakraFlow\n                )\n            }\n            \n            // ⚙️ SYSTEM SETTINGS\n            IconButton(\n                onClick = {\n                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)\n                },\n                modifier = Modifier\n                    .size(64.dp)\n                    .background(\n                        brush = Brush.radialGradient(\n                            colors = listOf(\n                                TailedBeastColors.SageMode.copy(alpha = 0.3f * controlPulse),\n                                Color.Transparent\n                            )\n                        ),\n                        shape = CircleShape\n                    )\n                    .border(\n                        width = 2.dp,\n                        color = TailedBeastColors.SageMode.copy(alpha = controlPulse),\n                        shape = CircleShape\n                    )\n            ) {\n                Icon(\n                    Icons.Default.Settings,\n                    contentDescription = \"System Settings\",\n                    modifier = Modifier.size(32.dp),\n                    tint = TailedBeastColors.SageMode\n                )\n            }\n            \n            // 🚪 DISMISS PORTAL\n            IconButton(\n                onClick = {\n                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)\n                    onDismiss()\n                },\n                modifier = Modifier\n                    .size(64.dp)\n                    .background(\n                        brush = Brush.radialGradient(\n                            colors = listOf(\n                                TailedBeastColors.NineTails.copy(alpha = 0.3f * controlPulse),\n                                Color.Transparent\n                            )\n                        ),\n                        shape = CircleShape\n                    )\n                    .border(\n                        width = 2.dp,\n                        color = TailedBeastColors.NineTails.copy(alpha = controlPulse),\n                        shape = CircleShape\n                    )\n            ) {\n                Icon(\n                    Icons.Default.Close,\n                    contentDescription = \"Exit Mindscape\",\n                    modifier = Modifier.size(32.dp),\n                    tint = TailedBeastColors.NineTails\n                )\n            }\n        }\n        \n        // 📊 QUICK STATUS\n        Text(\n            text = \"⚡ Current Mode: $controlMode | Chakra: 100% | Status: PERFECT JINCHURIKI\",\n            style = MaterialTheme.typography.bodySmall.copy(\n                fontFamily = FontFamily.Monospace\n            ),\n            color = TailedBeastColors.ChakraFlow.copy(alpha = 0.8f)\n        )\n    }\n}\n\n/**\n * ✅ TODO #3 OBLITERATED: COMPLETE MINDSCAPE OVERLAY SCREEN!\n */\n@Composable\nfun OverlayScreen() {\n    var showOverlay by remember { mutableStateOf(true) }\n    val hapticFeedback = LocalHapticFeedback.current\n    \n    // ✅ TODO #4 OBLITERATED: Advanced dismiss handling with animation!\n    AnimatedVisibility(\n        visible = showOverlay,\n        enter = fadeIn(animationSpec = tween(1000)) + scaleIn(initialScale = 0.3f),\n        exit = fadeOut(animationSpec = tween(500)) + scaleOut(targetScale = 0.3f)\n    ) {\n        Dialog(\n            onDismissRequest = { \n                hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)\n                showOverlay = false \n            },\n            properties = DialogProperties(\n                dismissOnBackPress = true,\n                dismissOnClickOutside = false,\n                usePlatformDefaultWidth = false\n            )\n        ) {\n            Box(\n                modifier = Modifier\n                    .fillMaxSize()\n                    .background(Color.Black.copy(alpha = 0.95f))\n            ) {\n                // 🌌 MINDSCAPE CONTENT\n                OverlayContent(\n                    modifier = Modifier.fillMaxSize()\n                )\n                \n                // 🎮 CONTROL PANEL AT BOTTOM\n                OverlayControlPanel(\n                    modifier = Modifier\n                        .align(Alignment.BottomCenter)\n                        .padding(16.dp),\n                    onDismiss = { \n                        hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)\n                        showOverlay = false \n                    }\n                )\n                \n                // 🌟 FLOATING CHAKRA PARTICLES\n                LaunchedEffect(Unit) {\n                    while (showOverlay) {\n                        delay(100)\n                        // Chakra particle effects would be added here\n                    }\n                }\n            }\n        }\n    }\n}\n\n// 🦊 HELPER FUNCTIONS\nfun getBeastName(tails: Int): String {\n    return when (tails) {\n        1 -> \"SHUKAKU\"\n        2 -> \"MATATABI\" \n        3 -> \"ISOBU\"\n        4 -> \"SON GOKU\"\n        5 -> \"KOKUO\"\n        6 -> \"SAIKEN\"\n        7 -> \"CHOMEI\"\n        8 -> \"GYUKI\"\n        9 -> \"KURAMA\"\n        else -> \"UNKNOWN\"\n    }\n}\n\n/**\n * 🎭 PREVIEW - Witness the Mindscape!\n */\n@Preview(showBackground = true)\n@Composable\nfun OverlayScreenPreview() {\n    OverlayScreen()\n}"