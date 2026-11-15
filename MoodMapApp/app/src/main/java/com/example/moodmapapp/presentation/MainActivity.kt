package com.example.moodmapapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.moodmapapp.domain.MoodEntry
import com.example.moodmapapp.ui.theme.MoodMapAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoodMapApp()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoodMapApp() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val containerColor = Color(0xFF2196F3)
    var showEmojiPicker by remember { mutableStateOf(value = false) }
    val snackbarHostState = remember { SnackbarHostState() }
    var moodEntries by remember { mutableStateOf( listOf<MoodEntry>()) }
    val scope = rememberCoroutineScope()
    var idCounter by remember { mutableIntStateOf(0) }

    MoodMapAppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Mood Map", color = Color.White) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = containerColor
                    )
                )
            },
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 },
                        icon = { Icon(Icons.Default.Home, contentDescription = null) },
                        label = { Text("TimeLine") }
                    )
                    NavigationBarItem(
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 },
                        icon = { Icon(Icons.Default.BarChart, contentDescription = null) },
                        label = { Text("Stats") }
                    )
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { showEmojiPicker = true },
                    containerColor = containerColor,
                    contentColor = Color.White
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add Mood"
                    )
                }
            },
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                when(selectedTab) {
                    0 -> TimeLineScreen(
                        blur = showEmojiPicker,
                        moodEntries = moodEntries,
                        onDelete = { entry ->
                            moodEntries = moodEntries - entry

                            // Show snackbar
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Delete mood"
                                )
                            }
                        }
                    )
                    1 -> StatsScreen(
                        blur = showEmojiPicker,
                        moodEntries = moodEntries
                    )
                }

                if (showEmojiPicker) {
                        EmojiPicker(
                            onEmojiSelected = { emoji ->
                                val description = MoodEntry.generateMoodMessage(emoji)
                                val entry = MoodEntry(emoji, description = description, idCounter++)
                                moodEntries = listOf(entry) + moodEntries

                                // Show snackbar
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "$emoji $description"
                                    )
                                }

                                showEmojiPicker = false
                            },
                            onDismiss = {
                                showEmojiPicker = false
                            }
                        ) 
                    }
            }
        }
    }
}

@Preview
@Composable
private fun MoodMapAppPreview() {
    MoodMapApp()
}