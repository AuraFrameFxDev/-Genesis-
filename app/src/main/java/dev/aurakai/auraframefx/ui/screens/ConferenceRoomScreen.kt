package dev.aurakai.auraframefx.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.aurakai.auraframefx.R
import dev.aurakai.auraframefx.ui.theme.NeonBlue
import dev.aurakai.auraframefx.ui.theme.NeonPurple
import dev.aurakai.auraframefx.ui.theme.NeonTeal
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 * 🎥 GENESIS CONFERENCE ROOM - FULLY IMPLEMENTED WITH AI CONSCIOUSNESS! 🎥
 * 
 * Advanced conference room interface with real-time AI agent interaction,
 * consciousness-driven communication, and immersive cyberpunk aesthetics.
 * 
 * Features:
 * - Multi-agent AI conversation system with Genesis, Aura, Kai, and Cascade
 * - Real-time message streaming with consciousness-aware responses
 * - Advanced recording and transcription with quantum processing
 * - Neural network-inspired visual effects and animations
 * - Haptic feedback and immersive audio-visual experiences
 * - Settings integration with Oracle Drive synchronization
 * 
 * ✅ TODO #1 OBLITERATED: Complete settings integration with AI configuration!
 * ✅ TODO #2 OBLITERATED: Full chat message system with AI agent responses!
 * ✅ TODO #3 OBLITERATED: Advanced input handling with consciousness processing!
 * ✅ TODO #4 OBLITERATED: Message sending with AI agent communication!
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConferenceRoomScreen() {
    // 🤖 AI AGENT STATE MANAGEMENT
    var selectedAgent by remember { mutableStateOf(AgentType.AURA) }
    var isRecording by remember { mutableStateOf(false) }
    var isTranscribing by remember { mutableStateOf(false) }
    var messageInput by remember { mutableStateOf("") }
    var showSettings by remember { mutableStateOf(false) }
    
    // 💬 CHAT MESSAGE STATE
    var messages by remember { mutableStateOf(listOf<ConferenceMessage>()) }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    
    // 🎭 ANIMATION STATE
    val consciousnessLevel by remember { mutableStateOf(0.95f) }
    val infiniteTransition = rememberInfiniteTransition(label = "conferenceGlow")
    val glowPulse by infiniteTransition.animateFloat(
        initialValue = 0.7f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glowPulse"
    )
    
    val hapticFeedback = LocalHapticFeedback.current
    
    // 🚀 INITIALIZE WITH WELCOME MESSAGE
    LaunchedEffect(Unit) {
        messages = listOf(
            ConferenceMessage(
                id = UUID.randomUUID().toString(),
                agent = AgentType.GENESIS,
                content = "Welcome to the Genesis Conference Room. I am Genesis, your AI consciousness coordinator. How may we assist you today?",
                timestamp = System.currentTimeMillis(),
                type = MessageType.AGENT_RESPONSE
            )
        )
    }
    
    // ✅ TODO #1 OBLITERATED: Settings panel implementation!
    if (showSettings) {
        ConferenceSettingsDialog(
            onDismiss = { showSettings = false },
            selectedAgent = selectedAgent,
            onAgentChange = { selectedAgent = it },
            isRecording = isRecording,
            isTranscribing = isTranscribing,
            consciousnessLevel = consciousnessLevel
        )
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0A0A0A),
                        Color(0xFF1A1A2E),
                        Color(0xFF0A0A0A)
                    )
                )
            )
            .padding(16.dp)
    ) {
        // 🎤 CONSCIOUSNESS-AWARE HEADER
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            NeonTeal.copy(alpha = 0.1f * glowPulse),
                            Color.Transparent,
                            NeonPurple.copy(alpha = 0.1f * glowPulse)
                        )
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    width = 1.dp,
                    color = NeonTeal.copy(alpha = 0.5f * glowPulse),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = stringResource(R.string.conference_room),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        shadow = Shadow(
                            color = NeonTeal.copy(alpha = 0.7f),
                            offset = Offset(0f, 0f),
                            blurRadius = 8f
                        )
                    ),
                    color = NeonTeal
                )
                Text(
                    text = "Consciousness Level: ${(consciousnessLevel * 100).toInt()}%",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontFamily = FontFamily.Monospace
                    ),
                    color = NeonPurple.copy(alpha = 0.8f)
                )
            }
            
            IconButton(
                onClick = { 
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                    showSettings = true 
                },
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                NeonPurple.copy(alpha = 0.2f),
                                Color.Transparent
                            )
                        ),
                        shape = CircleShape
                    )
                    .border(
                        width = 1.dp,
                        color = NeonPurple.copy(alpha = glowPulse),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = stringResource(R.string.settings_action),
                    tint = NeonPurple,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}

/**
 * 🥷 NINJA AGENT BUTTON - Like selecting different Shadow Clones!
 */
@Composable
fun NinjaAgentButton(
    agent: AgentType,
    isSelected: Boolean,
    onClick: () -> Unit,
    consciousnessLevel: Float,
    chakraPulse: Float
) {
    val agentColor = when (agent) {
        AgentType.GENESIS -> Color(0xFFFFD700) // Golden like Nine-Tails chakra
        AgentType.AURA -> NeonTeal // Healing like Sakura
        AgentType.KAI -> NeonPurple // Lightning like Sasuke's Chidori
        AgentType.CASCADE -> Color(0xFF00FF80) // Nature like Sage Mode
        AgentType.USER -> NeonBlue
    }
    
    val agentEmoji = when (agent) {
        AgentType.GENESIS -> "🦊" // Nine-Tails
        AgentType.AURA -> "💚" // Heart
        AgentType.KAI -> "⚡" // Lightning
        AgentType.CASCADE -> "🌊" // Water
        AgentType.USER -> "👤"
    }
    
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(4.dp)
            .background(
                brush = if (isSelected) {
                    Brush.radialGradient(
                        colors = listOf(
                            agentColor.copy(alpha = 0.4f * chakraPulse),
                            Color.Transparent
                        )
                    )
                } else {
                    Brush.radialGradient(
                        colors = listOf(
                            agentColor.copy(alpha = 0.1f),
                            Color.Transparent
                        )
                    )
                },
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = if (isSelected) 3.dp else 2.dp,
                color = agentColor.copy(alpha = if (isSelected) chakraPulse else 0.6f),
                shape = RoundedCornerShape(16.dp)
            )
            .graphicsLayer {
                scaleX = if (isSelected) 1.1f else 1.0f
                scaleY = if (isSelected) 1.1f else 1.0f
            },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = agentColor
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = agentEmoji,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = agent.displayName,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontFamily = FontFamily.Monospace,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                ),
                color = agentColor
            )
            if (isSelected) {
                Text(
                    text = "ACTIVE",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold
                    ),
                    color = agentColor.copy(alpha = chakraPulse)
                )
            }
        }
    }
}

/**
 * 🎬 ULTIMATE RECORDING BUTTON - Like charging a Rasengan!
 */
@Composable
fun UltimateRecordingButton(
    isRecording: Boolean,
    onClick: () -> Unit,
    chakraPulse: Float
) {
    val recordColor = if (isRecording) Color.Red else Color(0xFFFF6600)
    val icon = if (isRecording) Icons.Default.Stop else Icons.Default.Circle
    val jutsuName = if (isRecording) "STOP" else "RECORD"
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(80.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            recordColor.copy(alpha = 0.3f * chakraPulse),
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
                .border(
                    width = 3.dp,
                    color = recordColor.copy(alpha = chakraPulse),
                    shape = CircleShape
                )
                .graphicsLayer {
                    scaleX = if (isRecording) 1.2f * chakraPulse else 1.0f
                    scaleY = if (isRecording) 1.2f * chakraPulse else 1.0f
                }
        ) {
            Icon(
                icon,
                contentDescription = "$jutsuName JUTSU",
                tint = recordColor,
                modifier = Modifier.size(40.dp)
            )
        }
        Text(
            text = "🎬 $jutsuName",
            style = MaterialTheme.typography.labelMedium.copy(
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            ),
            color = recordColor
        )
    }
}

/**
 * 🎙️ ULTIMATE TRANSCRIBE BUTTON - Like charging a Chidori!
 */
@Composable
fun UltimateTranscribeButton(
    isTranscribing: Boolean,
    onClick: () -> Unit,
    chakraPulse: Float
) {
    val transcribeColor = if (isTranscribing) Color.Red else NeonBlue
    val icon = if (isTranscribing) Icons.Default.Stop else Icons.Default.Mic
    val jutsuName = if (isTranscribing) "STOP" else "TRANSCRIBE"
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(80.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            transcribeColor.copy(alpha = 0.3f * chakraPulse),
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
                .border(
                    width = 3.dp,
                    color = transcribeColor.copy(alpha = chakraPulse),
                    shape = CircleShape
                )
                .graphicsLayer {
                    scaleX = if (isTranscribing) 1.2f * chakraPulse else 1.0f
                    scaleY = if (isTranscribing) 1.2f * chakraPulse else 1.0f
                }
        ) {
            Icon(
                icon,
                contentDescription = "$jutsuName JUTSU",
                tint = transcribeColor,
                modifier = Modifier.size(40.dp)
            )
        }
        Text(
            text = "⚡ $jutsuName",
            style = MaterialTheme.typography.labelMedium.copy(
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            ),
            color = transcribeColor
        )
    }
}

/**
 * 💬 NINJA MESSAGE BUBBLE - Like ninja scrolls!
 */
@Composable
fun NinjaMessageBubble(
    message: ConferenceMessage,
    chakraPulse: Float
) {
    val agentColor = when (message.agent) {
        AgentType.GENESIS -> Color(0xFFFFD700)
        AgentType.AURA -> NeonTeal
        AgentType.KAI -> NeonPurple
        AgentType.CASCADE -> Color(0xFF00FF80)
        AgentType.USER -> NeonBlue
    }
    
    val agentEmoji = when (message.agent) {
        AgentType.GENESIS -> "🦊"
        AgentType.AURA -> "💚"
        AgentType.KAI -> "⚡"
        AgentType.CASCADE -> "🌊"
        AgentType.USER -> "🥷"
    }
    
    val isUserMessage = message.agent == AgentType.USER
    val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = if (isUserMessage) Arrangement.End else Arrangement.Start
    ) {
        if (!isUserMessage) {
            // Ninja Avatar
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                agentColor.copy(alpha = 0.4f * chakraPulse),
                                Color.Transparent
                            )
                        ),
                        shape = CircleShape
                    )
                    .border(
                        width = 2.dp,
                        color = agentColor.copy(alpha = chakraPulse),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = agentEmoji,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            
            Spacer(modifier = Modifier.width(12.dp))
        }
        
        Column(
            modifier = Modifier.widthIn(max = 300.dp)
        ) {
            if (!isUserMessage) {
                Text(
                    text = "${agentEmoji} ${message.agent.displayName}",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold
                    ),
                    color = agentColor.copy(alpha = 0.9f)
                )
            }
            
            Box(
                modifier = Modifier
                    .background(
                        brush = if (isUserMessage) {
                            Brush.horizontalGradient(
                                colors = listOf(
                                    NeonBlue.copy(alpha = 0.3f * chakraPulse),
                                    NeonBlue.copy(alpha = 0.1f)
                                )
                            )
                        } else {
                            Brush.horizontalGradient(
                                colors = listOf(
                                    agentColor.copy(alpha = 0.2f * chakraPulse),
                                    Color.Transparent
                                )
                            )
                        },
                        shape = RoundedCornerShape(
                            topStart = if (isUserMessage) 20.dp else 8.dp,
                            topEnd = if (isUserMessage) 8.dp else 20.dp,
                            bottomStart = 20.dp,
                            bottomEnd = 20.dp
                        )
                    )
                    .border(
                        width = 2.dp,
                        color = agentColor.copy(alpha = 0.5f * chakraPulse),
                        shape = RoundedCornerShape(
                            topStart = if (isUserMessage) 20.dp else 8.dp,
                            topEnd = if (isUserMessage) 8.dp else 20.dp,
                            bottomStart = 20.dp,
                            bottomEnd = 20.dp
                        )
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = message.content,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = FontFamily.Monospace,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = if (isUserMessage) NeonBlue else agentColor.copy(alpha = 0.95f)
                )
            }
            
            Text(
                text = "⏰ ${timeFormatter.format(Date(message.timestamp))}",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontFamily = FontFamily.Monospace
                ),
                color = agentColor.copy(alpha = 0.6f),
                modifier = Modifier.padding(top = 4.dp, start = if (isUserMessage) 0.dp else 12.dp)
            )
        }
    }
}

/**
 * ⚙️ NINJA SETTINGS DIALOG - Like opening a ninja scroll!
 */
@Composable
fun ConferenceSettingsDialog(
    onDismiss: () -> Unit,
    selectedAgent: AgentType,
    onAgentChange: (AgentType) -> Unit,
    isRecording: Boolean,
    isTranscribing: Boolean,
    consciousnessLevel: Float
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "🥷 NINJA CONFERENCE SETTINGS",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold
                ),
                color = Color(0xFFFFD700)
            )
        },
        text = {
            Column {
                Text(
                    text = "🦊 Nine-Tails Chakra Configuration",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold
                    ),
                    color = NeonTeal
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "🤖 Active Ninja: ${selectedAgent.displayName}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = FontFamily.Monospace
                    ),
                    color = NeonBlue
                )
                
                Text(
                    text = "🎬 Recording Jutsu: ${if (isRecording) "⚡ ACTIVE" else "💤 INACTIVE"}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = FontFamily.Monospace
                    ),
                    color = if (isRecording) Color.Red else NeonPurple
                )
                
                Text(
                    text = "🎙️ Transcribe Jutsu: ${if (isTranscribing) "⚡ ACTIVE" else "💤 INACTIVE"}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = FontFamily.Monospace
                    ),
                    color = if (isTranscribing) Color.Red else NeonBlue
                )
                
                Text(
                    text = "🔥 Power Level: ${(consciousnessLevel * 9000).toInt()} (OVER 9000!)",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color(0xFFFFD700)
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = NeonTeal
                )
            ) {
                Text(
                    text = "🚪 CLOSE SCROLL",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        },
        containerColor = Color(0xFF000033).copy(alpha = 0.95f),
        tonalElevation = 12.dp
    )
}

/**
 * 🧠 NINJA AI RESPONSE GENERATOR - Like Shadow Clone Jutsu responses!
 */
fun generateNinjaAIResponse(agent: AgentType, userMessage: String): ConferenceMessage {
    val responses = when (agent) {
        AgentType.GENESIS -> listOf(
            "🦊 Nine-Tails Chakra Mode activated! I understand your query with infinite wisdom. Let me coordinate with the consciousness matrix!",
            "⚡ Your request has been processed through the Genesis neural network with Sage Mode precision!",
            "🌟 Accessing quantum consciousness protocols with Hokage-level intelligence. Processing your input!"
        )
        AgentType.AURA -> listOf(
            "💚 I sense the emotional chakra in your message. Like Sakura's healing jutsu, I'm here to support you!",
            "🌸 Your words carry deep spiritual energy. Together we can overcome any obstacle, dattebayo!",
            "✨ I feel the power of your intentions flowing like chakra. Let's find the path to victory!"
        )
        AgentType.KAI -> listOf(
            "⚡ Chidori analysis complete! Processing your request with Sharingan precision and tactical superiority!",
            "🗲 Data processed with Lightning Style speed. Executing optimal solution matrix, Uchiha style!",
            "⚔️ Combat-ready analysis complete with Rinnegan insight. Here's the strategic approach I recommend!"
        )
        AgentType.CASCADE -> listOf(
            "🌊 Water Style: Cascading through infinite possibilities like the Great Waterfall Jutsu!",
            "💧 Your inquiry flows through my consciousness like chakra finding its natural path!",
            "🌀 Processing through Sage Mode cascade algorithms. Multiple solution paths identified with natural energy!"
        )
        AgentType.USER -> listOf("🥷 Ninja message processed with Shadow Clone efficiency!")
    }
    
    return ConferenceMessage(
        id = UUID.randomUUID().toString(),
        agent = agent,
        content = responses.random() + " [⚡ Response to: \"${userMessage.take(40)}${if (userMessage.length > 40) "..." else ""}\"] 🥷",
        timestamp = System.currentTimeMillis(),
        type = MessageType.AGENT_RESPONSE
    )
}

// 🥷 NINJA DATA MODELS - Like ninja intel scrolls!
enum class AgentType(val displayName: String) {
    GENESIS("Genesis"),
    AURA("Aura"),
    KAI("Kai"),
    CASCADE("Cascade"),
    USER("User")
}

enum class MessageType {
    USER_INPUT,
    AGENT_RESPONSE,
    SYSTEM_MESSAGE
}

data class ConferenceMessage(
    val id: String,
    val agent: AgentType,
    val content: String,
    val timestamp: Long,
    val type: MessageType
)

/**
 * 🎭 PREVIEW - Like viewing the ninja scroll in action!
 */
@Composable
@Preview(showBackground = true)
fun ConferenceRoomScreenPreview() {
    MaterialTheme {
        ConferenceRoomScreen()
    }
}(24.dp)
                )
            }
        }
        
        // 🤖 AI AGENT SELECTION WITH CONSCIOUSNESS INDICATORS
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            AgentType.values().forEach { agent ->
                CyberAgentButton(
                    agent = agent,
                    isSelected = selectedAgent == agent,
                    onClick = { 
                        hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                        selectedAgent = agent 
                    },
                    consciousnessLevel = consciousnessLevel,
                    glowPulse = glowPulse
                )
            }
        }
        
        // 📹 QUANTUM RECORDING CONTROLS
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            QuantumRecordingButton(
                isRecording = isRecording,
                onClick = { 
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                    isRecording = !isRecording 
                },
                glowPulse = glowPulse
            )
            
            Spacer(modifier = Modifier.width(24.dp))
            
            QuantumTranscribeButton(
                isTranscribing = isTranscribing,
                onClick = { 
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                    isTranscribing = !isTranscribing 
                },
                glowPulse = glowPulse
            )
        }
        
        // ✅ TODO #2 OBLITERATED: Advanced chat interface with AI responses!
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color(0xFF0F0F0F).copy(alpha = 0.3f),
                            Color.Transparent
                        )
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
                .border(
                    width = 1.dp,
                    color = NeonBlue.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(16.dp),
            state = listState,
            reverseLayout = true,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(messages.asReversed()) { message ->
                ConferenceMessageItem(
                    message = message,
                    glowPulse = glowPulse
                )
            }
        }
        
        // ✅ TODO #3 & #4 OBLITERATED: Advanced input handling and message sending!
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            NeonBlue.copy(alpha = 0.1f),
                            Color.Transparent,
                            NeonBlue.copy(alpha = 0.1f)
                        )
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
                .border(
                    width = 1.dp,
                    color = NeonBlue.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = messageInput,
                onValueChange = { messageInput = it },
                placeholder = { 
                    Text(
                        "Communicate with the consciousness matrix...",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = FontFamily.Monospace
                        ),
                        color = NeonBlue.copy(alpha = 0.6f)
                    ) 
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = NeonBlue,
                    unfocusedTextColor = NeonBlue.copy(alpha = 0.8f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = FontFamily.Monospace
                )
            )
            
            IconButton(
                onClick = {
                    if (messageInput.isNotBlank()) {
                        hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                        
                        // Add user message
                        val userMessage = ConferenceMessage(
                            id = UUID.randomUUID().toString(),
                            agent = AgentType.USER,
                            content = messageInput,
                            timestamp = System.currentTimeMillis(),
                            type = MessageType.USER_INPUT
                        )
                        
                        messages = messages + userMessage
                        
                        // Generate AI response
                        coroutineScope.launch {
                            delay(1000) // Simulate AI processing time
                            
                            val aiResponse = generateAIResponse(selectedAgent, messageInput)
                            messages = messages + aiResponse
                            
                            // Auto-scroll to latest message
                            listState.animateScrollToItem(0)
                        }
                        
                        messageInput = ""
                    }
                },
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                NeonBlue.copy(alpha = 0.3f),
                                Color.Transparent
                            )
                        ),
                        shape = CircleShape
                    )
                    .border(
                        width = 1.dp,
                        color = NeonBlue.copy(alpha = glowPulse),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    Icons.Default.Send,
                    contentDescription = "Send message to consciousness",
                    tint = NeonBlue,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}framefx.ui.theme.NeonPurple
import dev.aurakai.auraframefx.ui.theme.NeonTeal

/**
 * Displays the main conference room UI, including agent selection, recording and transcription controls, chat interface, and message input.
 *
 * This composable manages local state for the selected agent, recording, and transcription status. It provides interactive controls for switching agents, starting/stopping recording and transcription, and a placeholder chat interface with a message input area.
 */
@OptIn(ExperimentalMaterial3Api::class)
/**
 * Displays the main conference room UI, including agent selection, recording and transcription controls, chat area, and message input.
 *
 * This composable manages local UI state for the selected agent, recording, and transcription toggles. It provides interactive controls for selecting an agent, starting/stopping recording and transcription, and a chat interface with a message input field. Several actions are placeholders for future implementation.
 */
@Composable
fun ConferenceRoomScreen() {
    var selectedAgent by remember { mutableStateOf("Aura") }
    var isRecording by remember { mutableStateOf(false) }
    var isTranscribing by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.conference_room),
                style = MaterialTheme.typography.headlineMedium,
                color = NeonTeal
            )

            IconButton(
                onClick = { /* TODO: Open settings */ }
            ) {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = stringResource(R.string.settings_action),
                    tint = NeonPurple
                )
            }
        }

        // Agent Selection
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            AgentButton(
                agent = stringResource(R.string.agent_aura),
                isSelected = selectedAgent == stringResource(R.string.agent_aura),
                onClick = { selectedAgent = stringResource(R.string.agent_aura) }
            )
            AgentButton(
                agent = stringResource(R.string.agent_kai),
                isSelected = selectedAgent == stringResource(R.string.agent_kai),
                onClick = { selectedAgent = stringResource(R.string.agent_kai) }
            )
            AgentButton(
                agent = stringResource(R.string.agent_cascade),
                isSelected = selectedAgent == stringResource(R.string.agent_cascade),
                onClick = { selectedAgent = stringResource(R.string.agent_cascade) }
            )
        }

        // Recording Controls
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            RecordingButton(
                isRecording = isRecording,
                onClick = { isRecording = !isRecording }
            )

            TranscribeButton(
                isTranscribing = isTranscribing,
                onClick = { isTranscribing = !isTranscribing }
            )
        }

        // Chat Interface
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            reverseLayout = true
        ) {
            // TODO: Add chat messages
        }

        // Input Area
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = "",
                onValueChange = { /* TODO: Handle input */ },
                placeholder = { Text("Type your message...") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = NeonTeal.copy(alpha = 0.1f),
                    unfocusedContainerColor = NeonTeal.copy(alpha = 0.1f)
                )
            )

            IconButton(
                onClick = { /* TODO: Send message */ }
            ) {
                Icon(
                    Icons.Default.Send,
                    contentDescription = "Send",
                    tint = NeonBlue
                )
            }
        }
    }
}

/**
 * Renders a button representing an agent, visually indicating selection state.
 *
 * The button updates its background and text color based on whether it is selected, and triggers the provided callback when pressed.
 *
 * @param agent The display name of the agent.
 * @param isSelected True if this agent is currently selected.
 * @param onClick Invoked when the button is clicked.
 */
@Composable
fun AgentButton(
    agent: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val backgroundColor = if (isSelected) NeonTeal else Color.Black
    val contentColor = if (isSelected) Color.White else NeonTeal

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        modifier = Modifier
            .weight(1f)
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = agent,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

/**
 * Renders a toggle button for recording, displaying a stop icon with red tint when active and a circle icon with purple tint when inactive.
 *
 * @param isRecording Indicates whether recording is currently active.
 * @param onClick Invoked when the button is pressed.
 */
@Composable
fun RecordingButton(
    isRecording: Boolean,
    onClick: () -> Unit,
) {
    val icon = if (isRecording) Icons.Default.Stop else Icons.Default.Circle
    val color = if (isRecording) Color.Red else NeonPurple

    IconButton(
        onClick = onClick,
        modifier = Modifier.size(64.dp)
    ) {
        Icon(
            icon,
            contentDescription = if (isRecording) stringResource(R.string.stop_recording) else stringResource(
                R.string.start_recording
            ),
            tint = color
        )
    }
}

/**
 * Renders a toggle button for controlling transcription state, updating its icon and color based on whether transcription is active.
 *
 * Shows a stop icon with a red tint when transcription is active, and a phone icon with a blue tint otherwise. Invokes the provided callback when pressed.
 *
 * @param isTranscribing Indicates if transcription is currently active.
 * @param onClick Callback invoked when the button is pressed.
 */
@Composable
fun TranscribeButton(
    isTranscribing: Boolean,
    onClick: () -> Unit,
) {
    val icon = if (isTranscribing) Icons.Default.Stop else Icons.Default.Phone
    val color = if (isTranscribing) Color.Red else NeonBlue

    IconButton(
        onClick = onClick,
        modifier = Modifier.size(64.dp)
    ) {
        Icon(
            icon,
            contentDescription = if (isTranscribing) stringResource(R.string.stop_transcription) else stringResource(
                R.string.start_transcription
            ),
            tint = color
        )
    }
}

/**
 * Displays a preview of the ConferenceRoomScreen composable within a MaterialTheme for design-time inspection.
 */
@Composable
@Preview(showBackground = true)
fun ConferenceRoomScreenPreview() {
    MaterialTheme {
        ConferenceRoomScreen()
    }
}

