package nexenstrategy.technology.nexenauditedge.ui.composable.screen.bookings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nexenstrategy.technology.nexenauditedge.ui.composable.shared.LMAQNContentWrapper
import nexenstrategy.technology.nexenauditedge.ui.state.BookingUiState
import nexenstrategy.technology.nexenauditedge.ui.state.DataUiState
import nexenstrategy.technology.nexenauditedge.ui.theme.NexenSuccess
import nexenstrategy.technology.nexenauditedge.ui.viewmodel.BookingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookingsScreen(modifier: Modifier = Modifier, viewModel: BookingViewModel = koinViewModel()) {
    val state by viewModel.bookingsState.collectAsState()
    var selected by remember { mutableStateOf<String?>(null) }
    LMAQNContentWrapper(
        dataState = state,
        dataPopulated = { BookingList((state as DataUiState.Populated).data, modifier) { selected = it } },
        dataEmpty = {
            Column(Modifier.fillMaxSize().padding(32.dp), verticalArrangement = Arrangement.Center) {
                Text("No bookings yet", style = MaterialTheme.typography.headlineSmall)
                Text("Browse services and reserve your first consultation.", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        },
    )
    selected?.let { number ->
        AlertDialog(
            onDismissRequest = { selected = null },
            title = { Text("Cancel this booking?") },
            text = { Text("Your reserved consultation will be removed.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.cancelBooking(number)
                        selected = null
                    },
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = { TextButton(onClick = { selected = null }) { Text("Keep booking") } },
        )
    }
}

@Composable
private fun BookingList(bookings: List<BookingUiState>, modifier: Modifier, onCancel: (String) -> Unit) {
    LazyColumn(modifier.fillMaxSize(), contentPadding = androidx.compose.foundation.layout.PaddingValues(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item { Text("Your consultations", style = MaterialTheme.typography.headlineSmall) }
        items(bookings, key = { it.bookingNumber }) { booking ->
            Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp)) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(booking.serviceName, style = MaterialTheme.typography.titleMedium)
                        Text("Confirmed", color = NexenSuccess, style = MaterialTheme.typography.labelLarge)
                    }
                    Text(booking.timestamp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("Session #${booking.bookingNumber}", style = MaterialTheme.typography.bodyMedium)
                    Text("Your consultant will meet you online at the appointed time.", style = MaterialTheme.typography.bodyMedium)
                    TextButton(onClick = { onCancel(booking.bookingNumber) }) { Text("Cancel", color = MaterialTheme.colorScheme.error) }
                }
            }
        }
    }
}
