import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedLogoView(
    dim: Float = 24f,
    name: String = "Hello",
    bgGradient1: Brush = Brush.horizontalGradient(listOf(Color.Cyan, Color.Blue)),
    bgGradient2: Brush = Brush.horizontalGradient(listOf(Color.Blue, Color.Green)),
    fgColor1: Color = Color.Magenta,
    fgColor2: Color = Color.White
) {
    var rotation by remember { mutableStateOf(0f) }
    var scale by remember { mutableStateOf(1f) }
    var showText by remember { mutableStateOf(false) }
    var clipWidth by remember { mutableStateOf(-130f) }

    val backgroundPhase1 = !showText
    val fgColor = if (showText) fgColor2 else if (backgroundPhase1) fgColor1 else fgColor2
    val bgColor = if (backgroundPhase1) bgGradient1 else bgGradient2

    Box(
        modifier = Modifier.fillMaxSize().background(bgColor),
        contentAlignment = Alignment.Center
    ) {
        Box {
            val spacing = 3.dp
            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing)
            ) {
                LeftRectangle(dim = dim, fgColor, spacing = spacing)
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing),
                ) {
                    SmallSquare(dim = dim, fgColor = fgColor)
                    SmallSquare(dim = dim, fgColor = fgColor)
                }
            }
        }
    }

}

@Composable
fun SmallSquare(dim: Float, fgColor: Color) {
    Box(
        modifier = Modifier
            .size(dim.dp)
            .background(fgColor)
    )
}

@Composable
fun LeftRectangle(dim: Float, fgColor: Color, spacing: Dp) {
    Column(
        verticalArrangement = Arrangement.spacedBy(spacing),
        modifier = Modifier.background(fgColor)
    ) {
        SmallSquare(dim = dim, fgColor = fgColor)
        SmallSquare(dim = dim, fgColor = fgColor)
    }
}

@Preview(showBackground = true)
@Composable
fun AnimatedLogoViewPreview() {
    val sanguine1 = Color(212,20,90)
    val sanguine2 = Color(251, 176, 59)
    AnimatedLogoView(name = "NICE", fgColor1 = Color.White, fgColor2 = sanguine2, bgGradient1 = Brush.linearGradient(
        listOf(sanguine1, sanguine2)
    ))
}