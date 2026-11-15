package com.example.gameoflifeconway

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CancelPresentation
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ControlView(
    speed: Float,
    isPlaying: Boolean,
    showDesignSelectionView: Boolean,
    onTogglePlayPause: (Boolean) -> Unit,
    onRefresh: () -> Unit,
    onClear: () -> Unit,
    onToggleImage: () -> Unit,
    onChangeImage: () -> Unit,
    onToggleVisibility: () -> Unit,
    onSpeedChange: (Float) -> Unit,
) {
    Column {

        Slider(
            value = speed,
            onValueChange = onSpeedChange,
            valueRange = 1f..300f,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            ControlButton(
                icon = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                onClick = { onTogglePlayPause(!isPlaying) }
            )

            ControlButton(
                icon = Icons.Default.Refresh,
                onClick = onRefresh
            )

            ControlButton(
                icon = Icons.Default.CancelPresentation,
                onClick = onClear
            )

            ControlButton(
                icon = Icons.Filled.Image,
                onClick = onToggleImage
            )

            ControlButton(
                icon = Icons.Filled.Shuffle,
                onClick = onChangeImage
            )

            ControlButton(
                icon = if (showDesignSelectionView) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                onClick = onToggleVisibility
            )


        }
    }
}

@Composable
fun ControlButton(
    icon: ImageVector,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(36.dp)
            .background(Color.Gray.copy(alpha = 0.2f), shape = CircleShape)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(18.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewControlView() {
    var showImage by remember {
        mutableStateOf(true)
    }
    var currentImage by remember {
        mutableStateOf("autumn1")
    }
    var speed by remember {
        mutableFloatStateOf(200.0f)
    }
    var isPlaying by remember {
        mutableStateOf(true)
    }
    var showDesignSelectionView by remember {
        mutableStateOf(true)
    }
    val board = BoardModel(gridSize = 50)
    ControlView(
        speed = speed,
        isPlaying = isPlaying,
        showDesignSelectionView = showDesignSelectionView,
        onTogglePlayPause = {},
        onRefresh = { board.randomBoard() },
        onClear = { board.clearBoard() },
        onToggleImage = { showImage = !showImage },
        onChangeImage = {},
        onToggleVisibility = { showDesignSelectionView = !showDesignSelectionView },
        onSpeedChange = {speed = it}
    )
}

