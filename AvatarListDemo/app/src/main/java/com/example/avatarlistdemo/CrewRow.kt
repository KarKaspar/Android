package com.example.avatarlistdemo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun CrewRowPreview() {
    CrewRow(
        member = CrewMember(
            name = "Dr. Vega",
            mission = "Mars Habitat",
            avatarRes = R.drawable.user2
        )
    )
}

@Composable
fun CrewRow(member: CrewMember) {
    var isSelected by rememberSaveable {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                isSelected = !isSelected
            }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            CrewAvatar(
                imageRes = member.avatarRes,
                isSelected = isSelected
            )
            Spacer(modifier = Modifier.width(16.dp))
            CrewInfo(
                name = member.name,
                mission = member.mission
            )
        }

        Checkbox(
            checked = isSelected,
            onCheckedChange = {
                isSelected = it
            }
        )
    }
}