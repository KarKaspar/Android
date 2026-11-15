package com.example.codingexercises

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DivisibleBy7App() {
    var input by remember { mutableStateOf(TextFieldValue(text = "")) }
    var result by remember { mutableStateOf(value = "") }

    val colors = MaterialTheme.colorScheme

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colors.primary.copy(alpha = 0.2f),
                        colors.secondaryContainer.copy(alpha = 0.2f)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 20.dp),
            verticalArrangement = Arrangement.spacedBy(space=16.dp)
        ) {
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                label = { Text(text = "Enter a number") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    val number = input.text.toIntOrNull()
                    result = if (number != null) divisibleBy7(number) else "Please enter a valid number"
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Check")
            }

            if (result.isNotEmpty()) {
                Text(
                    text = result,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
        }
    }


}

fun divisibleBy7(number: Int): String {
    if (number % 7 == 0) {
        return "I am $number and I am divisible by 7"
    } else {
        return "I am $number and I am not divisible by 7"
    }
}


@Preview(showBackground = true)
@Composable
private fun DivisibleBy7Preview() {
    DivisibleBy7App()
}