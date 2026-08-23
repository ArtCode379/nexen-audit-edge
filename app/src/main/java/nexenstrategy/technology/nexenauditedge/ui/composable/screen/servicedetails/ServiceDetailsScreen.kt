package nexenstrategy.technology.nexenauditedge.ui.composable.screen.servicedetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import nexenstrategy.technology.nexenauditedge.data.model.ServiceModel
import nexenstrategy.technology.nexenauditedge.ui.composable.shared.LMAQNContentWrapper
import nexenstrategy.technology.nexenauditedge.ui.state.DataUiState
import nexenstrategy.technology.nexenauditedge.ui.viewmodel.ServiceDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ServiceDetailsScreen(serviceId: Int, modifier: Modifier = Modifier, viewModel: ServiceDetailsViewModel = koinViewModel(), onNavigateToCheckout: (serviceId: Int) -> Unit) {
    val state by viewModel.serviceState.collectAsState()
    LaunchedEffect(serviceId) { viewModel.observeServiceById(serviceId) }
    LMAQNContentWrapper(
        dataState = state,
        dataPopulated = { Details((state as DataUiState.Populated).data, modifier, onNavigateToCheckout) },
        dataEmpty = { Text("Service details are unavailable.", Modifier.padding(24.dp)) },
    )
}

@Composable
private fun Details(service: ServiceModel, modifier: Modifier, onBook: (Int) -> Unit) {
    Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        AsyncImage(service.imageUrl, null, Modifier.fillMaxWidth().height(280.dp).clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)), contentScale = ContentScale.Crop)
        Column(Modifier.padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Surface(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), shape = RoundedCornerShape(50)) {
                Text(service.category, Modifier.padding(horizontal = 12.dp, vertical = 6.dp), color = MaterialTheme.colorScheme.primary)
            }
            Text(service.name, style = MaterialTheme.typography.headlineSmall)
            Text("From £${service.price.toInt()} · ${service.durationMinutes} min", style = MaterialTheme.typography.titleMedium)
            Text(service.description, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text("What is included", style = MaterialTheme.typography.titleLarge)
            service.features.forEach { feature ->
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Icon(Icons.Outlined.CheckCircle, null, tint = MaterialTheme.colorScheme.secondary)
                    Text(feature)
                }
            }
            Text("Available times", style = MaterialTheme.typography.titleMedium)
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(service.availableTime.orEmpty()) { time ->
                    Card { Text(time.toString(), Modifier.padding(horizontal = 14.dp, vertical = 9.dp)) }
                }
            }
            Button(onClick = { onBook(service.id) }, modifier = Modifier.fillMaxWidth()) { Text("Book Consultation") }
        }
    }
}
