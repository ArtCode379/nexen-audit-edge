package nexenstrategy.technology.nexenauditedge.ui.composable.screen.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import nexenstrategy.technology.nexenauditedge.R
import nexenstrategy.technology.nexenauditedge.ui.theme.NexenAccent
import nexenstrategy.technology.nexenauditedge.ui.theme.NexenPrimary
import nexenstrategy.technology.nexenauditedge.ui.viewmodel.LMAQNSplashVM
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(modifier: Modifier = Modifier, viewModel: LMAQNSplashVM = koinViewModel(), onNavigateToHomeScreen: () -> Unit, onNavigateToOnboarding: () -> Unit) {
    val onboarded by viewModel.onboardedState.collectAsStateWithLifecycle()
    SplashScreenContent(modifier)
    LaunchedEffect(onboarded) {
        delay(1500)
        if (onboarded) onNavigateToHomeScreen() else onNavigateToOnboarding()
    }
}

@Composable
fun SplashScreenContent(modifier: Modifier = Modifier) {
    var visible by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (visible) 1f else 0.8f, tween(800), label = "logoScale")
    LaunchedEffect(Unit) { visible = true }
    Box(modifier.fillMaxSize().background(Brush.verticalGradient(listOf(NexenPrimary, NexenAccent))), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(20.dp)) {
            Box(Modifier.size(112.dp).scale(scale).background(Color.White, RoundedCornerShape(28.dp)), contentAlignment = Alignment.Center) {
                Image(painterResource(R.drawable.lmaqn_ic_launcher_foreground), null, Modifier.size(88.dp))
            }
            Text("Nexen Audit Edge", color = Color.White, style = MaterialTheme.typography.titleLarge)
            Text("Clarity for every technology decision", color = Color.White.copy(alpha = 0.8f), style = MaterialTheme.typography.bodyMedium)
        }
    }
}
