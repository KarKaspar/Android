package com.example.animation1

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DemoAnimateCornerRadius() {
    var isLargeCorner by remember { mutableStateOf(false)}

    val cornerRadius by animateIntAsState(
        targetValue = if (isLargeCorner) 90 else 10,
        //animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy), // goesoutside the limits
        label = "Corner Redius Animation"
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(cornerRadius))
                .background(Color.Cyan)
        )

        ElevatedButton(
            onClick = {
                isLargeCorner = !isLargeCorner
            }
        )
        {
            Text("Tere")
        }
    }
}
/*
@Preview(showBackground = true)
@Composable
private fun PreviewStateAnimation() {
    DemoAnimateCornerRadius()
}
*/


@Composable
fun DemoAnimateOpacity() {
    var isVisible by remember { mutableStateOf(true)}

    val opacity by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearEasing
            //delayMillis = 1000
        )
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(150.dp)
                .alpha(opacity)
                .background(Color.Green)
        )

        ElevatedButton(
            onClick = {
                isVisible = !isVisible
            }
        )
        {
            Text("Tere")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAnimateOpacity() {
    DemoAnimateOpacity()
}