package com.example.geoblur.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import com.example.geoblur.viewModel.GameViewModel

@Preview(showBackground = true)
@Composable
fun GeoBlurPreview() {
    GeoBlurScreen()
}

@Composable
fun GeoBlurScreen(
    viewModel: GameViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.loadLandmarksFromAssets(context)
    }

    if (uiState.loading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        val currentLandmark = viewModel.currentLandmark

        if (currentLandmark == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Bo landmark to show.")
            }
        } else {
            var imageLoading by remember { mutableStateOf(true) }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                CustomProgressBar(
                    progress = viewModel.progress
                )

                Text(
                    text = "Guess the Landmark!",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        letterSpacing = 1.5.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(16.dp)
                )

                // Middle section - Image
                val imageUrl = currentLandmark.images.firstOrNull()
                Log.d("GeoBlur", "Image URL = $imageUrl")
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
//                        .aspectRatio(1f)
//                        .clip(MaterialTheme.shapes.medium),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "Landmark Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clip(MaterialTheme.shapes.medium)
                            .blur(uiState.blurRadius.dp),
                        onState = {
                            // Check if the image is currently loading
                            imageLoading = it is AsyncImagePainter.State.Loading
                        }
                    )

                    if (imageLoading) {
                        CircularProgressIndicator()
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Bottom section - Buttons
                Column {
                    val shuffledOptions = remember(currentLandmark) { currentLandmark.options.shuffled() }

                    shuffledOptions.forEach { option ->
                        Button(
                            onClick = {
                                // submit answer using 'option'
                                viewModel.submitAnswer(option)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Text(option)
                        }
                    }
                }

                val playerWon = viewModel.playerWon

                if (uiState.showDialog) {
                    AlertDialog(
                        onDismissRequest = {},
                        confirmButton = {
                            Button(
                                onClick = {
                                    viewModel.restartGame()
                                }
                            ) {
                                Text("Play Again")
                            }
                        },
                        title = {
                            Text(if (playerWon) "Congratulations!" else "Game Over")
                        },
                        text = {
                            Text(
                                if (playerWon)
                                    "You've identified all landmarks correctly!"
                                else
                                    "Better luck next time! The image is now fully revealed."
                            )
                        }
                    )
                }
            }
        }
    }

}
