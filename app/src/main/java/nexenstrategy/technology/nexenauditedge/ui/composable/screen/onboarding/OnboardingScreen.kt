package nexenstrategy.technology.nexenauditedge.ui.composable.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.outlined.CloudQueue
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import nexenstrategy.technology.nexenauditedge.R
import nexenstrategy.technology.nexenauditedge.ui.viewmodel.LMAQNOnboardingVM
import org.koin.androidx.compose.koinViewModel

private data class Page(val title: String, val description: String, val image: Int, val icon: ImageVector)
private val pages = listOf(
    Page("See risk clearly", "Independent security and systems audits reveal the priorities that matter most.", R.drawable.onboarding_security, Icons.Outlined.Security),
    Page("Build a practical roadmap", "Translate technology goals into sequenced, cost-aware decisions your team can deliver.", R.drawable.onboarding_cloud, Icons.Outlined.CloudQueue),
    Page("Book expert guidance", "Choose a service, reserve an initial consultation, and keep every session detail close at hand.", R.drawable.onboarding_strategy, Icons.Outlined.Analytics),
)

@Composable
fun OnboardingScreen(modifier: Modifier = Modifier, viewModel: LMAQNOnboardingVM = koinViewModel(), onNavigateToHomeScreen: () -> Unit) {
    val completed by viewModel.onboardingSetState.collectAsState()
    LaunchedEffect(completed) { if (completed) onNavigateToHomeScreen() }
    val pager = rememberPagerState { pages.size }
    Column(modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("NEXEN STRATEGY", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.height(20.dp))
        HorizontalPager(state = pager, modifier = Modifier.weight(1f)) { index ->
            val page = pages[index]
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Box(Modifier.size(72.dp).background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), RoundedCornerShape(20.dp)), contentAlignment = Alignment.Center) {
                    Icon(page.icon, null, Modifier.size(40.dp), tint = MaterialTheme.colorScheme.primary)
                }
                Spacer(Modifier.height(20.dp))
                Text(page.title, style = MaterialTheme.typography.headlineSmall)
                Spacer(Modifier.height(10.dp))
                Text(page.description, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(Modifier.height(20.dp))
                Image(painterResource(page.image), null, Modifier.fillMaxWidth().height(150.dp), contentScale = ContentScale.Crop)
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            pages.indices.forEach { index ->
                Box(
                    Modifier
                        .size(if (index == pager.currentPage) 10.dp else 7.dp)
                        .background(
                            if (index == pager.currentPage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                            CircleShape,
                        ),
                )
            }
        }
        Spacer(Modifier.height(20.dp))
        Button(onClick = viewModel::setOnboarded, modifier = Modifier.fillMaxWidth(), enabled = pager.currentPage == pages.lastIndex) {
            Text(if (pager.currentPage == pages.lastIndex) "Get Started" else "Swipe to continue")
        }
    }
}
