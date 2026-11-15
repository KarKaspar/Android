package com.example.avatarlistdemo

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun CrewAvatarPreview() {
    CrewAvatar(
        imageRes = R.drawable.user1,
        isSelected = true
    )
}

@Composable
fun CrewAvatar(imageRes: Int?, isSelected: Boolean) {
    val borderColor = if (isSelected) Color.Red else Color.Green
    Image(
        painter = painterResource(
            id = imageRes ?: R.drawable.blank_profile
        ),
        contentDescription = "Crew avatar",
        modifier = Modifier
            .size(72.dp)
            .border(
                width = 4.dp,
                color = borderColor,
                shape = CircleShape
            )
            .padding(3.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}





