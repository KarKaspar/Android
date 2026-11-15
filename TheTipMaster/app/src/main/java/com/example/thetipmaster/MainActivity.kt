package com.example.thetipmaster

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Brightness6
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.LocalBar
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thetipmaster.ui.theme.TheTipMasterTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Get SharedPreferences to store the app launch count
        val sharedPreferences = getSharedPreferences(
            "App_prefs",
            Context.MODE_PRIVATE
        )
        val launchCount = sharedPreferences.getInt("launch_count", 0)

        // Logcat - Debugging
        Log.d("LaunchCount", "Current launch count: $launchCount")

        // Increase launch count
        sharedPreferences.edit().putInt("launch_count", launchCount + 1).apply()

        // Logcat - Debugging
        Log.d("LaunchCount", "Updated launch count: $launchCount")


        setContent {
            var darkTheme by remember { mutableStateOf(false) }
            var showSplash by remember { mutableStateOf(launchCount < 3) }

            if (showSplash) {
                SplashScreen { showSplash = false }
            } else {
                TheTipMasterTheme(darkTheme = darkTheme) {
                    TipMasterApp(onToggleTheme = { darkTheme = !darkTheme })
                }
            }
        }
    }
}

@Composable
fun TipMasterApp(onToggleTheme: () -> Unit) {
    var billAmount by remember { mutableStateOf("") }
    var peopleCountInput by remember { mutableStateOf("1") }
    val peopleCount: Int = peopleCountInput.toIntOrNull() ?: 1

    var selectedTipPercentage by remember { mutableDoubleStateOf(0.15) } // Default tip
    var customTip by remember { mutableStateOf("") }

    val tipPercentage = if (customTip.isNotBlank()) {
        (customTip.toDoubleOrNull() ?: selectedTipPercentage * 100) / 100.0
    } else {
        selectedTipPercentage
    }

    val bill = billAmount.toDoubleOrNull() ?: 0.0
    val tip = bill * tipPercentage
    val total = bill + tip
    val tipPerPerson = if (peopleCount > 0) tip / peopleCount else 0.0
    val totalPerPerson = if (peopleCount > 0) total / peopleCount else 0.0

    // We want to tap out of the text field
    val focusManager = LocalFocusManager.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("TipMaster", style = MaterialTheme.typography.headlineMedium)
                IconButton(onClick = onToggleTheme) {
                    Icon(
                        imageVector = Icons.Default.Brightness6,
                        contentDescription = "Toggle theme"
                    )
                }
            }

            // Enter Bill Amount
            OutlinedTextField(
                value = billAmount,
                onValueChange = { billAmount = it },
                label = { Text("Enter bill amount") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Number of people to split the bill
            OutlinedTextField(
                value = peopleCountInput,
                onValueChange = { peopleCountInput = it },
                label = { Text("Number of people to split the bill") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Select Tip Percentage
            Text(
                text = "Select Tip Percentage",
                style = MaterialTheme.typography.titleMedium
            )

            TipPercentagePicker(
                selectedTipPercentage = selectedTipPercentage,
                onTipSelected = { selectedTipPercentage = it },
                customTip = customTip,
                onCustomTipChanged = { customTip = it }
            )

            Spacer(modifier = Modifier.height(24.dp))
            // Summary
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SummaryRow(
                    label = "Tip",
                    amount = tip,
                    icon = Icons.Default.AttachMoney
                )

                SummaryRow(
                    label = "Total",
                    amount = total,
                    icon = Icons.Default.Money
                )

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                SummaryRow(
                    label = "Tip Per Person",
                    amount = tipPerPerson,
                    icon = Icons.Default.Person
                )

                SummaryRow(
                    label = "Total Per Person",
                    amount = totalPerPerson,
                    icon = Icons.Default.Group
                )
            }

        }
    }
}

@Composable
fun TipPercentagePicker(
    selectedTipPercentage: Double,
    onTipSelected: (Double) -> Unit,
    customTip: String,
    onCustomTipChanged: (String) -> Unit
) {
    val options = listOf(0.05, 0.10, 0.15, 0.20)
    val labels = listOf("5%", "10%", "15%", "20%")

    val focusManager = LocalFocusManager.current

    Column {
        // Default Tips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            options.forEachIndexed { index, option ->
                val isSelected = option == selectedTipPercentage
                val backgroundColor = if (isSelected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.surfaceVariant
                }

                TipButton(
                    text = labels[index],
                    color = backgroundColor,
                    onClick = {
                        onTipSelected(option)
                        onCustomTipChanged("") // Clear Text Field
                        focusManager.clearFocus() // Dismiss keyboard
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Enter custom tip percentage
        OutlinedTextField(
            value = customTip,
            onValueChange = onCustomTipChanged,
            label = { Text("Enter custom tip percentage (e.g., 18%)") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )


    }
}

@Composable
fun SplashScreen(
    onNavigateToMainScreen: () -> Unit
) {
    val gradientColors = listOf(Color(0xFF6A1B9A), Color(0xFFFF4081))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(gradientColors))
            .padding(16.dp)
            .clickable { onNavigateToMainScreen() },
        contentAlignment = Alignment.Center
    ) {
        Column {
            Icon(
                imageVector = Icons.Default.LocalBar,
                contentDescription = "Splash Icon",
                modifier = Modifier.size(120.dp),
                tint = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "TipMaster",
                style = MaterialTheme.typography.headlineMedium.copy(color = Color.White)
            )
        }
    }

    LaunchedEffect(Unit) {
        delay(3000)
        onNavigateToMainScreen()
    }
}

@Composable
fun SummaryRow(
    label: String,
    amount: Double,
    icon: ImageVector
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 8.dp)
            )
            Text(label, style = MaterialTheme.typography.bodyLarge)
        }

        Text(
            "$${"%.2f".format(amount)}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun TipButton(
    text: String,
    color: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(color, shape = CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen {  }
}

@Preview(showBackground = true)
@Composable
fun TipMasterPreview() {
    TipMasterApp(onToggleTheme = {})
}