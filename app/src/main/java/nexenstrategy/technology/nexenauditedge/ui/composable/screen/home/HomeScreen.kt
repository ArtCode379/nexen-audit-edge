package nexenstrategy.technology.nexenauditedge.ui.composable.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CloudQueue
import androidx.compose.material.icons.outlined.DataObject
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.SettingsSuggest
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import nexenstrategy.technology.nexenauditedge.data.model.ServiceModel
import nexenstrategy.technology.nexenauditedge.ui.composable.shared.LMAQNContentWrapper
import nexenstrategy.technology.nexenauditedge.ui.state.DataUiState
import nexenstrategy.technology.nexenauditedge.ui.theme.NexenAccent
import nexenstrategy.technology.nexenauditedge.ui.theme.NexenPrimary
import nexenstrategy.technology.nexenauditedge.ui.viewmodel.ServiceViewModel
import org.koin.androidx.compose.koinViewModel

private val categories = listOf(
    "Cybersecurity" to Icons.Outlined.Security,
    "Cloud" to Icons.Outlined.CloudQueue,
    "Data & AI" to Icons.Outlined.DataObject,
    "Optimisation" to Icons.Outlined.SettingsSuggest,
)

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ServiceViewModel = koinViewModel(),
    onNavigateToServiceDetails: (serviceId: Int) -> Unit,
) {
    val state by viewModel.servicesState.collectAsState()
    LMAQNContentWrapper(
        dataState = state,
        dataPopulated = {
            HomeContent((state as DataUiState.Populated).data, modifier, onNavigateToServiceDetails)
        },
        dataEmpty = { Text("New advisory services are being prepared.", modifier = Modifier.padding(24.dp)) },
    )
}

@Composable
private fun HomeContent(services: List<ServiceModel>, modifier: Modifier, onOpen: (Int) -> Unit) {
    LazyColumn(modifier.fillMaxSize(), contentPadding = androidx.compose.foundation.layout.PaddingValues(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item {
            Text("Technology decisions, made clearer", style = MaterialTheme.typography.headlineLarge)
            Text("Independent advice for secure, resilient growth.", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        item {
            Card(shape = RoundedCornerShape(16.dp)) {
                Box(Modifier.fillMaxWidth().height(150.dp).background(Brush.horizontalGradient(listOf(NexenPrimary, NexenAccent)))) {
                    Column(Modifier.padding(20.dp).align(Alignment.CenterStart), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Next available", color = Color.White.copy(alpha = 0.8f), style = MaterialTheme.typography.labelLarge)
                        Text("Tomorrow · 09:30", color = Color.White, style = MaterialTheme.typography.headlineSmall)
                        Text("Reserve a focused discovery session", color = Color.White)
                    }
                }
            }
        }
        item {
            Text("Explore by expertise", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(10.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                items(categories) { category -> CategoryCard(category.first, category.second) }
            }
        }
        item { Text("Recommended services", style = MaterialTheme.typography.titleLarge) }
        items(services, key = { it.id }) { service -> ServiceCard(service) { onOpen(service.id) } }
        item {
            Surface(color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.08f), shape = RoundedCornerShape(14.dp)) {
                Column(Modifier.padding(18.dp)) {
                    Text("Knowledge brief", color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.labelLarge)
                    Text("Three signals your digital operating model needs attention", style = MaterialTheme.typography.titleMedium)
                    Text("Explore governance, cloud cost, and security trends with our practical expert insights.", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Composable
private fun CategoryCard(label: String, icon: ImageVector) {
    Card(shape = RoundedCornerShape(14.dp)) {
        Column(Modifier.size(112.dp).padding(12.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Icon(icon, null, tint = MaterialTheme.colorScheme.primary)
            Text(label, style = MaterialTheme.typography.labelLarge)
        }
    }
}

@Composable
private fun ServiceCard(service: ServiceModel, onClick: () -> Unit) {
    Card(Modifier.fillMaxWidth().clickable(onClick = onClick), shape = RoundedCornerShape(14.dp)) {
        Row(Modifier.padding(12.dp), horizontalArrangement = Arrangement.spacedBy(14.dp)) {
            AsyncImage(service.imageUrl, null, Modifier.size(96.dp), contentScale = ContentScale.Crop)
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(service.category, color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.labelLarge)
                Text(service.name, style = MaterialTheme.typography.titleMedium)
                Text(service.description, maxLines = 2, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("From £${service.price.toInt()}", style = MaterialTheme.typography.labelLarge)
                    Text("Book now", color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}
