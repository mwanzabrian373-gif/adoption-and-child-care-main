package com.example.adoption_and_childcare.ui.compose

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ModernLandingPagePreview() {
    MaterialTheme {
        ModernLandingPage(onGetStarted = {}, onLogin = {})
    }
}

/**
 * PROPOSED DESIGN FOR HUMAN APPROVAL
 * This is a modern, high-conversion landing page structure.
 * Features:
 * 1. Hero Section with Entrance Animations
 * 2. Glassmorphic Feature Cards
 * 3. Testimonial / Impact Section
 * 4. Sticky CTA (Call to Action)
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModernLandingPage(
    onGetStarted: () -> Unit,
    onLogin: () -> Unit
) {
    val scrollState = rememberScrollState()
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isVisible = true
    }

    Scaffold(
        bottomBar = {
            BottomStickyCTA(onGetStarted)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(scrollState)
                .background(Color(0xFFF8F9FA))
        ) {
            // 1. HERO SECTION
            HeroSection(isVisible, onLogin)

            // 2. IMPACT STATS
            ImpactStatsSection(isVisible)

            // 3. KEY FEATURES
            FeaturesSection()

            // 4. TESTIMONIALS / QUOTE
            QuoteSection()

            Spacer(modifier = Modifier.height(100.dp)) // Padding for sticky bottom bar
        }
    }
}

@Composable
fun HeroSection(isVisible: Boolean, onLogin: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
            .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF4CAF50), Color(0xFF2E7D32))
                )
            )
    ) {
        // Decorative Circles
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = Color.White.copy(alpha = 0.05f),
                radius = 400f,
                center = androidx.compose.ui.geometry.Offset(size.width * 0.9f, 0f)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn() + slideInVertically(initialOffsetY = { -50 })
            ) {
                Surface(
                    shape = CircleShape,
                    color = Color.White.copy(alpha = 0.2f),
                    modifier = Modifier.size(80.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ChildCare,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.padding(16.dp).size(48.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Building Families,\nNurturing Dreams",
                style = MaterialTheme.typography.displaySmall,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                lineHeight = 42.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "The all-in-one platform for modern adoption management and child welfare tracking.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White.copy(alpha = 0.85f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            TextButton(onClick = onLogin) {
                Text(
                    "Already have an account? Sign In",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    textDecoration = androidx.compose.ui.text.style.TextDecoration.Underline
                )
            }
        }
    }
}

@Composable
fun ImpactStatsSection(isVisible: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .offset(y = (-30).dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatCard("1,200+", "Children Placed", Modifier.weight(1f))
        StatCard("450+", "Active Families", Modifier.weight(1f))
        StatCard("98%", "Success Rate", Modifier.weight(1f))
    }
}

@Composable
fun StatCard(value: String, label: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(100.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(value, fontWeight = FontWeight.Bold, color = Color(0xFF4CAF50), fontSize = 18.sp)
            Text(label, style = MaterialTheme.typography.labelSmall, color = Color.Gray, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun FeaturesSection() {
    Column(modifier = Modifier.padding(24.dp)) {
        Text(
            text = "Everything you need",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A1A1A)
        )
        Spacer(modifier = Modifier.height(20.dp))

        FeatureRow(Icons.Default.Speed, "Real-time Tracking", "Monitor case progress and milestones instantly.")
        FeatureRow(Icons.Default.Lock, "Secure Documents", "Military-grade encryption for all sensitive records.")
        FeatureRow(Icons.Default.Groups, "Family Matching", "Smart algorithms to find the perfect home for every child.")
    }
}

@Composable
fun FeatureRow(icon: ImageVector, title: String, desc: String) {
    Row(
        modifier = Modifier.padding(vertical = 12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFFE8F5E9),
            modifier = Modifier.size(48.dp)
        ) {
            Icon(icon, contentDescription = null, tint = Color(0xFF4CAF50), modifier = Modifier.padding(12.dp))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(desc, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
    }
}

@Composable
fun QuoteSection() {
    Card(
        modifier = Modifier.padding(24.dp).fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2196F3).copy(alpha = 0.05f)),
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, Color(0xFF2196F3).copy(alpha = 0.1f))
    ) {
        Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.FormatQuote, contentDescription = null, tint = Color(0xFF2196F3), modifier = Modifier.size(48.dp))
            Text(
                text = "\"This platform transformed how we manage our case load. We've seen a 30% increase in placement speed.\"",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("— Sarah Jenkins, Senior Case Manager", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun BottomStickyCTA(onGetStarted: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 16.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp).navigationBarsPadding(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Ready to start?", fontWeight = FontWeight.Bold)
                Text("Join 500+ agencies worldwide", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
            }
            Button(
                onClick = onGetStarted,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
            ) {
                Text("Get Started", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
            }
        }
    }
}
