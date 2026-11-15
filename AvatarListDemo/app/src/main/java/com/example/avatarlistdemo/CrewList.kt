package com.example.avatarlistdemo

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun CrewListPreview() {
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

    CrewList(
        crewMembers = crewMembers
    )
}

@Composable
fun CrewList(crewMembers: List<CrewMember>) {
    LazyColumn {
        items(crewMembers) { member ->
            CrewRow(member = member)

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}







