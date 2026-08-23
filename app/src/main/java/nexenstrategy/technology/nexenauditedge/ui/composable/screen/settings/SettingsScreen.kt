package nexenstrategy.technology.nexenauditedge.ui.composable.screen.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    SettingsScreenContent(modifier)
}

@Composable
fun SettingsScreenContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("About", style = MaterialTheme.typography.headlineSmall)
        Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp)) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                InfoRow(Icons.Outlined.Business, "Company", "NEXEN STRATEGY LIMITED")
                InfoRow(Icons.Outlined.Verified, "App version", "1.0.0")
            }
        }
        Text("Support & legal", style = MaterialTheme.typography.titleLarge)
        Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp)) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Questions about a service, booking, or your audit? Our team is ready to help.", color = MaterialTheme.colorScheme.onSurfaceVariant)
                Button(
                    onClick = { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://nexenstrategy.study"))) },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Icon(Icons.Outlined.Language, null)
                    Text("  Customer Support")
                }
                Text("Privacy and service information is available on the company website.", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
private fun InfoRow(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Icon(icon, null, tint = MaterialTheme.colorScheme.primary)
        Column {
            Text(label, style = MaterialTheme.typography.labelLarge)
            Text(value, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
