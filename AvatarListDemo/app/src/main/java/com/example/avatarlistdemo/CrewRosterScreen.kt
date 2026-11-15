package com.example.avatarlistdemo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun CrewRosterScreen() {
    val crewMembers = listOf(
        CrewMember(
            name = "Commander Nova",
            mission = "Mission: Lunar Frontier",
            avatarRes = R.drawable.user1
        ),
        CrewMember(
            name = "Dr. Vega",
            mission = "Mission: Mars Habitat",
            avatarRes = R.drawable.user2
        ),
        CrewMember(
            name = "Captain Orion",
            mission = "Mission: Europa Ice Probe",
            avatarRes = R.drawable.user3
        ),
        CrewMember(
            name = "Engineer Lyra",
            mission = "Mission: Titan Terraforming"
        )
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
               text = "Space Crew Roster",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Tap a checkbox to mark a crew member as ready for launch.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            CrewList(crewMembers = crewMembers)
        }
    }
}

@Preview
@Composable
private fun CrewRosterScreenPreview() {
    CrewRosterScreen()
}