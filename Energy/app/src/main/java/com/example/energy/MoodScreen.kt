package com.example.energy

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun MoodScreen(
    vm: MoodViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val energy = vm.energy
    val mood = vm.mood

    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF0B132B), Color(0xFF1C2541))
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Spacer(modifier = Modifier.height(8.dp))

        Column() {
            Text(
                text = "CURRENT VIBE",
                color = Color.Gray,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = mood,
                style = TextStyle(
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFFFA726),
                            Color(0xFFFF4081)
                        )
                    )
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EnergyLevel(energy = energy)
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Your vitality is ${ if (energy > 70) "surging" else "stable"}",
                color = Color.LightGray,
                fontSize = 14.sp
            )

        }

        // Buttons
        Column(
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            Text(
                text = "TUNE YOUR ENERGY",
                color = Color.Gray,
                fontSize = 12.sp
            )

            ActionButton(
                title = "Drink Coffee",
                subtitle = "Boost focus +15%",
                gradient = listOf(Color(0xFFFFA726), Color(0xFFFF7043)),
                imageVector = Icons.Filled.Coffee
            ) {
                 vm.drinkCoffee()
            }

            Spacer(modifier = Modifier.height(16.dp))

            ActionButton(
                title = "Take a Nap",
                subtitle = "Restore base energy",
                gradient = listOf(Color(0xFF7E57C2), Color(0xFF9575CD)),
                imageVector = Icons.Filled.DarkMode
            ) {
                vm.takeNap()
            }

            Spacer(modifier = Modifier.height(16.dp))

            ActionButton(
                title = "Scroll Social Media",
                subtitle = "Mental release -5%",
                gradient = listOf(Color(0xFF263238), Color(0xFF37474F)),
                imageVector = Icons.Filled.Smartphone
            ) {
                vm.scrollSocialMedia()
            }
        }

    }
}





@Preview
@Composable
private fun MoodScreenPreview() {
    MoodScreen()
}
