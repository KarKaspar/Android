package com.example.codingexercises.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DivisibleByDApp() {
    var numberInput by remember { mutableStateOf(TextFieldValue(text = "")) }
    var minInput by remember { mutableStateOf(TextFieldValue(text = "")) }
    var maxInput by remember { mutableStateOf(TextFieldValue(text = "")) }
    var results by remember { mutableStateOf(listOf<String>()) }

    val colors = MaterialTheme.colorScheme
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colors.primary.copy(alpha = 0.5f),
                        colors.secondaryContainer.copy(alpha = 0.3f)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = numberInput,
                onValueChange = { numberInput = it},
                label = {Text("Enter a number")},
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = minInput,
                    onValueChange = { minInput = it},
                    label = {Text("Min divisor")},
                    modifier = Modifier.fillMaxWidth()
                        .weight(1f)
                )
                OutlinedTextField(
                    value = maxInput,
                    onValueChange = { maxInput = it},
                    label = {Text("Max divisor")},
                    modifier = Modifier.fillMaxWidth()
                        .weight(1f)
                )
            }

            Button(
                onClick = {
                    val number = numberInput.text.toIntOrNull()
                    val min = minInput.text.toIntOrNull()
                    val max = maxInput.text.toIntOrNull()
                    results = if (number != null && min != null && max != null && min < max) {
                        rangeDivisibleByD(number, min, max)
                    } else {
                        listOf("Please enter valid inputs (min < max).")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Check")
            }

            // TODO: Scrollable results
            LazyColumn {
                items(results) { line -> 
                    Text(
                        text = line,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }

}



// Logic functions
fun divisibleByD(number: Int, divisor: Int): String {
    return when {
        divisor == 0 -> "Division by zero is not allowed."
        number % divisor == 0 -> "I am $number and I am divisible by $divisor"
        else -> "I am $number and I am not divisible by $divisor"
    }
}

fun rangeDivisibleByD(number: Int, min: Int, max: Int): List<String> {
    return (min until max)
        .map { divisibleByD(number, it) }
}

@Preview
@Composable
private fun DivisiblyByDPreview() {
    DivisibleByDApp()
}