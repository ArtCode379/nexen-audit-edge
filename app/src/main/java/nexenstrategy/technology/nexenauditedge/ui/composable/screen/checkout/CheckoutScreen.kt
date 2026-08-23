package nexenstrategy.technology.nexenauditedge.ui.composable.screen.checkout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import nexenstrategy.technology.nexenauditedge.data.entity.BookingEntity
import nexenstrategy.technology.nexenauditedge.ui.state.DataUiState
import nexenstrategy.technology.nexenauditedge.ui.viewmodel.CheckoutViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CheckoutScreen(
    serviceId: Int,
    modifier: Modifier = Modifier,
    viewModel: CheckoutViewModel = koinViewModel(),
    onNavigateToBookingsScreen: () -> Unit,
) {
    val bookingState by viewModel.orderState.collectAsStateWithLifecycle()
    val emailInvalid by viewModel.emailInvalidState.collectAsStateWithLifecycle()
    var phone by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    if (bookingState is DataUiState.Populated) {
        CheckoutDialog((bookingState as DataUiState.Populated<BookingEntity>).data, onNavigateToBookingsScreen)
    }
    CheckoutContent(
        firstName = viewModel.customerFirstName,
        lastName = viewModel.customerLastName,
        email = viewModel.customerEmail,
        phone = phone,
        notes = notes,
        selectedDate = selectedDate,
        isEmailInvalid = emailInvalid,
        modifier = modifier,
        onFirstNameChanged = viewModel::updateCustomerFirstName,
        onLastNameChanged = viewModel::updateCustomerLastName,
        onEmailChanged = viewModel::updateCustomerEmail,
        onPhoneChanged = { phone = it },
        onNotesChanged = { notes = it },
        onDateChanged = { selectedDate = it },
        onConfirm = { viewModel.placeBooking(serviceId) },
    )
}

@Composable
private fun CheckoutContent(
    firstName: String,
    lastName: String,
    email: String,
    phone: String,
    notes: String,
    selectedDate: String,
    isEmailInvalid: Boolean,
    modifier: Modifier,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onNotesChanged: (String) -> Unit,
    onDateChanged: (String) -> Unit,
    onConfirm: () -> Unit,
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val complete = firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank() && phone.isNotBlank() && selectedDate.isNotBlank()
    Column(modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("Book a consultation", style = MaterialTheme.typography.headlineSmall)
        Text("Tell us how to reach you and choose a preferred date. We will confirm the exact time.", color = MaterialTheme.colorScheme.onSurfaceVariant)
        CheckoutTextField(firstName, onFirstNameChanged, "First name", Modifier.fillMaxWidth())
        CheckoutTextField(lastName, onLastNameChanged, "Last name", Modifier.fillMaxWidth())
        CheckoutTextField(email, onEmailChanged, "Email", Modifier.fillMaxWidth(), isError = isEmailInvalid, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email))
        CheckoutTextField(phone, onPhoneChanged, "Phone", Modifier.fillMaxWidth(), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone))
        OutlinedTextField(
            value = selectedDate,
            onValueChange = {},
            readOnly = true,
            label = { Text("Preferred date") },
            trailingIcon = { Icon(Icons.Outlined.CalendarMonth, null) },
            modifier = Modifier.fillMaxWidth().clickable { showDatePicker = true },
        )
        OutlinedTextField(notes, onNotesChanged, Modifier.fillMaxWidth(), label = { Text("Notes (optional)") }, minLines = 3)
        Button(onClick = onConfirm, enabled = complete, modifier = Modifier.fillMaxWidth()) { Text("Confirm Booking") }
    }
    if (showDatePicker) {
        val picker = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    picker.selectedDateMillis?.let { millis ->
                        val date = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate()
                        onDateChanged(date.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")))
                    }
                    showDatePicker = false
                }) { Text("Select") }
            },
            dismissButton = { TextButton(onClick = { showDatePicker = false }) { Text("Cancel") } },
        ) { DatePicker(picker) }
    }
}

@Composable
fun CheckoutTextField(
    input: String,
    onInputChange: (String) -> Unit,
    labelText: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    OutlinedTextField(
        value = input,
        onValueChange = onInputChange,
        modifier = modifier,
        enabled = enabled,
        label = { Text(labelText) },
        isError = isError,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = MaterialTheme.colorScheme.primary),
    )
}
